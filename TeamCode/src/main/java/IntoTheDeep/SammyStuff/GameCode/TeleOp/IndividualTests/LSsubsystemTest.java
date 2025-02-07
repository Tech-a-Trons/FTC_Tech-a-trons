package IntoTheDeep.SammyStuff.GameCode.TeleOp.IndividualTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import IntoTheDeep.SammyStuff.GameCode.subsystems.LinearSlides;
@TeleOp(name = "lsSubsystemTest")
public class LSsubsystemTest extends LinearOpMode {

    LinearSlides ls;

    int target;

    @Override
    public void runOpMode() throws InterruptedException {
        ls = new LinearSlides(hardwareMap, "lsl", "lsr");
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.b) {

                target = 300;
            }

            if (gamepad2.a) {
                ls.setManualPower(0);
            }


            ls.PIDloop(target);
        }
    }
}
