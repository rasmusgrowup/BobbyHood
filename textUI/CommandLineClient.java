/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BobbyHood.textUI;

import BobbyHood.Domain.Command;
import BobbyHood.Domain.Commands;
import BobbyHood.Domain.Game;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;

    public CommandLineClient() {
        game = new Game();
        parser = new Parser(game);
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished && !game.isCompleted()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Bobby Hood! \n");
        System.out.println("The game where you can help reduce extreme poverty.");
        System.out.println("Type '" + Commands.HELP + "' to see available commands.");
        System.out.println();
        System.out.println("Let the game begin!");
        System.out.println();
        johnStartMessage();
        System.out.println();
        System.out.println(game.getRoomDescription());
    }

    private void johnStartMessage() {
        System.out.println(game.getJohnStartMessage());
    }

    private void printHelp() {
        for(String str : game.getCommandDescriptions())
        {
            System.out.println(str + " ");
        }
    }

    //Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Commands commandWord = command.getCommandName();

        if (commandWord == Commands.UNKNOWN) {
            System.out.println("Please type a valid command. Type 'help' to see available commands.");
            return false;
        }

        if (commandWord == Commands.HELP) {
            //System.out.println("You are lost. You are alone. You wander");
            //System.out.println("around at the university.");
            System.out.println();
            System.out.println("Your command words are:");
            printHelp();
        } else if (commandWord == Commands.GO) {
            if (game.goRoom(command)) {
                System.out.println(game.getRoomDescription());
            } else {
                System.out.println("Can't walk in that direction.");
            }
        } else if (commandWord == Commands.DESCRIBE) {
            if (game.describe(command)) {
                System.out.println(game.getPersonDescription());
            } else {
                System.out.println("Can't describe a person, that doesn't exist.");
            }
        } else if (commandWord == Commands.TALK) {
            if (game.talkTo(command)) {
                game.startDialog(command);
            } else if (command.getCommandValue() == null) {
                System.out.println("Please choose a person");
            } else {
                System.out.println("Nobody here, is called " + command.getCommandValue());
            }
        } else if (commandWord == Commands.OPEN) {
            if (game.open(command).equals("inventory")) {
                System.out.println(game.getInventory());
            } else if (game.open(command).equals("handbook")) {
                System.out.println(game.getHandbook());
            } else if (game.open(command).equals("empty")) {
                System.out.println("Can't open nothing");
            } else {
                System.out.println("Open what?");
            }
        } else if (commandWord == Commands.QUIT) {
            if (game.quit(command)) {
                wantToQuit = true;
            } else {
                System.out.println("Quit what?");
            }
        }
        return wantToQuit;
    }
}
