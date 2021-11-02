package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;

import ggc.WarehouseManager;
import ggc.exceptions.UnavailableFileException;
import ggc.app.exceptions.FileOpenFailedException;

//FIXME import classes

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    addStringField("open_filename", Prompt.openFile());
    //FIXME maybe add command fields
  }

  @Override
  public final void execute() throws CommandException {

    try {
      String _filename = stringField("open_filename");
      _receiver.load(_filename);
    }
    catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    }
    catch (ClassNotFoundException e) {
      throw new FileOpenFailedException(stringField("open_filename"));
    }
    catch (FileNotFoundException e) {
      throw new FileOpenFailedException(stringField("open_filename"));
    }
    catch (IOException e) {
      throw new FileOpenFailedException(stringField("open_filename"));
    }

  }

}
