package BobbyHood.Domain;

public class CommandImplementation implements Command
{
    private final Commands commandName;
    private final String commandValue;

    public CommandImplementation(Commands commandWord, String secondWord)
    {
        this.commandName = commandWord;
        this.commandValue = secondWord;
    }


    @Override
    public Commands getCommandName()
    {
        return commandName;
    }

    @Override
    public String getCommandValue()
    {
        return commandValue;
    }


    @Override
    public boolean isUnknown()
    {
        return (commandName == Commands.UNKNOWN);
    }

    @Override
    public boolean hasCommandValue()
    {
        return (commandValue != null);
    }
}

