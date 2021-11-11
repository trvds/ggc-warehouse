package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import java.io.IOException;
import java.io.FileNotFoundException;
import ggc.WarehouseManager;
import ggc.app.exceptions.FileOpenFailedException;
import ggc.exceptions.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    try {
      _receiver.save();
    }
    catch (MissingFileAssociationException e) {
      String save_filename = Form.requestString(Prompt.newSaveAs());
      try {
        _receiver.saveAs(save_filename);
      }
      catch (MissingFileAssociationException mfae) {
        mfae.printStackTrace();
      }
      catch (FileNotFoundException fnfe) {
        fnfe.printStackTrace();
      }
      catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
    catch (FileNotFoundException e) {
      throw new FileOpenFailedException(e.getMessage());
    }
    catch (IOException e) {
      throw new FileOpenFailedException(e.getMessage());
    }
  }
}
