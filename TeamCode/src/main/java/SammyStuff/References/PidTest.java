package SammyStuff.References;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@TeleOp(name = "PID test")

public class PidTest extends LinearOpMode {
    private PIDController controller;
    public static double p = 0,i = 0,d = 0;
    public static double f = 0;

    public static int target = 0;
    public final double ticksInDegree = 700 / 180.0;

    private DcMotor Arm_Motor;
    @Override
    public void runOpMode() throws InterruptedException {
    controller = new PIDController(p,i,d);
    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    Arm_Motor = hardwareMap.get(DcMotor.class, "fl");
    waitForStart();
    while(opModeIsActive()){
        controller.setPID(p, i, d);
        int armPos = Arm_Motor.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target/ticksInDegree))*f;
        double power =  pid + ff;
        Arm_Motor.setPower(power);
    }
    }
}
