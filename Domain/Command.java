
package BobbyHood.Domain;

public interface Command {

    Commands getCommandName();

    String getCommandValue();

    boolean hasCommandValue();

    boolean isUnknown();
    
}
