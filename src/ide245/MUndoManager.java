
package ide245;

import javax.swing.event.UndoableEditEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;


public class MUndoManager extends UndoManager {

	private CompoundEdit compoundEdit;
	// This allows us to start combining operations.
	// it will be reset after the first change.
	private boolean startCombine = false;
	// This holds the start of the last line edited, if edits are on multiple
	// lines, then they will not be combined.
	private int	lastLine = -1;

	public MUndoManager(MDocument doc) {
		doc.addUndoableEditListener(this);
		lastLine = doc.getStartPosition().getOffset();
	}

	/**
	 *  Whenever an UndoableEdit happens the edit will either be absorbed
	 *  by the current compound edit or a new compound edit will be started
	 */
	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		//  Start a new compound edit

		AbstractDocument.DefaultDocumentEvent docEvt = (DefaultDocumentEvent) e.getEdit();

		if (compoundEdit == null) {
			compoundEdit = startCompoundEdit(e.getEdit());
			startCombine = false;
			return;
		}

		int editLine = ((MDocument)docEvt.getDocument()).getLineNumberAt(docEvt.getOffset());

		//  Check for an incremental edit or backspace.
		//  The Change in Caret position and Document length should both be
		//  either 1 or -1.
		if ((startCombine || Math.abs(docEvt.getLength()) == 1) && editLine == lastLine) {
			compoundEdit.addEdit(e.getEdit());
			startCombine = false;
			return;
		}

		//  Not incremental edit, end previous edit and start a new one
		lastLine = editLine;

		compoundEdit.end();
		compoundEdit = startCompoundEdit(e.getEdit());
	}

	/*
	 **  Each CompoundEdit will store a group of related incremental edits
	 **  (ie. each character typed or backspaced is an incremental edit)
	 */
	private CompoundEdit startCompoundEdit(UndoableEdit anEdit) {
		//  Track Caret and Document information of this compound edit
		AbstractDocument.DefaultDocumentEvent docEvt = (DefaultDocumentEvent) anEdit;

		//  The compound edit is used to store incremental edits

		compoundEdit = new MyCompoundEdit();
		compoundEdit.addEdit(anEdit);

		//  The compound edit is added to the UndoManager. All incremental
		//  edits stored in the compound edit will be undone/redone at once

		addEdit(compoundEdit);

		return compoundEdit;
	}

	class MyCompoundEdit extends CompoundEdit {

		@Override
		public boolean isInProgress() {
			//  in order for the canUndo() and canRedo() methods to work
			//  assume that the compound edit is never in progress
			return false;
		}

		@Override
		public void undo() throws CannotUndoException {
			//  End the edit so future edits don't get absorbed by this edit

			if (compoundEdit != null) {
				compoundEdit.end();
			}

			super.undo();

			//  Always start a new compound edit after an undo

			compoundEdit = null;
		}
	}

	/**
	 * Start to combine the next operations together.  Only the next operation is combined.
	 * The flag is then automatically reset.
	 */
	public void startCombine() {
		startCombine = true;
	}
}