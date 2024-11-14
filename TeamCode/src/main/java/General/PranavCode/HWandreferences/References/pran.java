package General.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "hewo_telemetry")
public class pran extends LinearOpMode {
    @Override

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Init");
        telemetry.update();
        waitForStart();
        telemetry.addLine("Pranpran");
        telemetry.addData("fl",10);
        telemetry.update();
        telemetry.addData("br", "%.4f", 10.3264387623876);
        telemetry.addData("bl", "%.4ff", 10.421534153562);
    }
}