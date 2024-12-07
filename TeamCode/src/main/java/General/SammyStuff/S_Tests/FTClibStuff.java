package General.SammyStuff.S_Tests;





import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.KeyReader;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FTClibStuff extends LinearOpMode {
//    Motor m_motor_1 = new Motor(hardwareMap, "m_motor_1");
//    Motor m_motor_2 = new Motor(hardwareMap, "motorTwo", Motor.GoBILDA.RPM_312);
//    Motor m_motor_3 = new Motor(hardwareMap, "motorThree");
//
//    // grab the internal DcMotor object
//    DcMotor motorOne = m_motor_1.motor;
    Motor fslsLeft = new Motor(hardwareMap,"lsl" );
    Motor fslsRight = new Motor(hardwareMap,"lsr");
    MotorGroup LS = new MotorGroup(fslsLeft,fslsRight);
    @Override
    public void runOpMode() throws InterruptedException {

    }
}
