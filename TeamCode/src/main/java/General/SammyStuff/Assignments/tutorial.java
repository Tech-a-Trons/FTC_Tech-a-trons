package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Sammy_motor_thingy_:)",group = "Autonomous" )
public class tutorial extends OpMode {
    DcMotor WEEWOO;

    @Override
    public void init() {
        WEEWOO = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {

        long start_time = System.currentTimeMillis();
        long time_interval = 5000;
        long endtime = start_time + time_interval;
        while (System.currentTimeMillis() < endtime) {
            if (gamepad1.left_trigger > 0.05) {
                WEEWOO.setPower(1);
            } else {
                WEEWOO.setPower(0);
            }
        }
        long start_time1 = System.currentTimeMillis();
        long time_interval1 = 5000;
        long endtime1 = start_time1 + time_interval1;
        while (System.currentTimeMillis() < endtime1) {
            if (gamepad1.left_trigger > 0.05) {
                WEEWOO.setPower(-0.5);
            } else {
                WEEWOO.setPower(0);
            }
        }
    }
}                                    