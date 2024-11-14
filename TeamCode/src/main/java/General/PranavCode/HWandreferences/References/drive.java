package General.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "PranavDrive")

public class drive extends OpMode {
    DcMotor fl1 = null;
    DcMotor fr1 = null;
    DcMotor bl1 = null;
    DcMotor br1 = null;

    @Override
    public void init() {
        fl1 = hardwareMap.get(DcMotor.class, "FL");
        fr1 = hardwareMap.get(DcMotor.class, "FR");
        bl1 = hardwareMap.get(DcMotor.class, "BL");
        br1 = hardwareMap.get(DcMotor.class, "BR");
        
        fl1.setDirection(DcMotorSimple.Direction.REVERSE);
        bl1.setDirection(DcMotorSimple.Direction.REVERSE);
        fr1.setDirection(DcMotorSimple.Direction.FORWARD);
        br1.setDirection(DcMotorSimple.Direction.FORWARD);
        
        fl1.setPower(0);
        fr1.setPower(0);
        bl1.setPower(0);
        br1.setPower(0);
    }
    
    @Override
    public void loop() {
        long sts = System.currentTimeMillis();
        long tis = 5000;
        long ets = sts + tis;
        while (System.currentTimeMillis() < ets) {
            fl1.setPower(.5);
            fr1.setPower(.5);
            bl1.setPower(.5);
            br1.setPower(.5);
        }
        
        long st2 = System.currentTimeMillis();
        long ti2 = 10000;
        long et2 = st2 + ti2;
        while (System.currentTimeMillis() < et2) {
            fl1.setPower(0);
            fr1.setPower(0);
            bl1.setPower(0);
            br1.setPower(0);
        }
    }
}
