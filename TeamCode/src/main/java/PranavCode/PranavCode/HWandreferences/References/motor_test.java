package PranavCode.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "AND_WELCOME_THE_WORLD_CHAMPIONS,_EVERGREEEEEEEEEN_TECH-AAAAAAAAAA-TRONNNNNNNNNNNNNNNS")

public class motor_test extends OpMode {

    DcMotor namemorot;

    @Override
    public void init() {
        namemorot = hardwareMap.get(DcMotor.class, "numero1");

    }

    @Override
    public void loop() {
        namemorot.setPower(-1);
    }
}
