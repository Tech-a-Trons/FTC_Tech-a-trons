package General.SammyStuff.S_Tests.FTClibTest;

import com.arcrobotics.ftclib.command.CommandBase;
import General.SammyStuff.GameCode.subsystems.specClaw;

public class CommandTest extends CommandBase {
    specClaw specClaw;

    public CommandTest(specClaw SpecClaw) {
        specClaw = SpecClaw;

        addRequirements(specClaw);
    }

}

