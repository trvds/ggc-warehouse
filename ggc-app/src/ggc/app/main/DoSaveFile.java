package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import ggc.WarehouseManager;
//FIXME import classes
import ggc.exceptions.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
    if (receiver.getFilename() == "") {
      addStringField("filename", Prompt.newSaveAs());
    }
    //FIXME maybe add command fields
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    try {
      String _filename = stringField("filename");
      if (_receiver.getFilename() == "") {
        _filename = stringField("filename");
        _receiver.saveAs(_filename);
      }
      else {
      _receiver.save();
      }
    }
    catch (MissingFileAssociationException e){
      //FIXME
      e.printStackTrace();

    }
    catch (FileNotFoundException e){
      e.printStackTrace();
      //FIXME
    }
    catch (IOException e){
      e.printStackTrace();
      //FIXME
    }



    }




}
