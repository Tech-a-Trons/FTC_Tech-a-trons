package PranavCode.PranavCode.HWandreferences.References;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "senthil")
public class senthil_1 extends OpMode {
    DcMotor motor_name;


    @Override
    public void init() {
        motor_name = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {

    }
}
