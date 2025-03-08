package IntoTheDeep.SammyStuff.S_Tests.FTClibTest;

import com.arcrobotics.ftclib.command.CommandBase;
import IntoTheDeep.SammyStuff.GameCode.subsystems.FTCLIB.specClaw;

public class CommandTest extends CommandBase {
    specClaw specClaw;

    public CommandTest(specClaw SpecClaw) {
        specClaw = SpecClaw;

        addRequirements(specClaw);
    }

}

