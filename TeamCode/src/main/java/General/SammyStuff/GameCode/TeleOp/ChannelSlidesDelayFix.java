package General.SammyStuff.GameCode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@TeleOp(name = "channelSlidesfix")
    public class ChannelSlidesDelayFix extends LinearOpMode {

        CRServo ChannelSlides;
        boolean ChannelSlidesExtended = false;
        ElapsedTime ChannelSlidesTime = new ElapsedTime();
         public static boolean Currentx;
         public static boolean Previousx;
        @Override
        public void runOpMode() throws InterruptedException {
            ChannelSlides = hardwareMap.get(CRServo.class, "channelSlides");

            Gamepad currentGamepad1 = new Gamepad();
            Gamepad currentGamepad2 = new Gamepad();

            Gamepad previousGamepad1 = new Gamepad();
            Gamepad previousGamepad2 = new Gamepad();

            waitForStart();
            while (opModeIsActive()) {

                previousGamepad1.copy(currentGamepad1);
                previousGamepad2.copy(currentGamepad2);

                currentGamepad1.copy(gamepad1);
                currentGamepad2.copy(gamepad2);

               if (currentGamepad2.x){
                    Currentx = true;
                }else if(!currentGamepad2.x){
                   Currentx = false;
               }

                if (previousGamepad2.x){
                    Previousx = true;
                }else if(!previousGamepad2.x){
                    Previousx = false;
                }



                if (Currentx && !Previousx) {
                    ChannelSlidesExtended = !ChannelSlidesExtended;
                }
                if (ChannelSlidesExtended) {
                    ChannelSlidesTime.reset();
                    ChannelSlides.setPower(-1);
                    if (ChannelSlidesTime.milliseconds() >= 1500) {
                        ChannelSlides.setPower(-0.5);
                    }
                } else if (!ChannelSlidesExtended) {
                    ChannelSlidesTime.reset();
                    ChannelSlides.setPower(1);
                    if (ChannelSlidesTime.milliseconds() >= 1500) {
                        ChannelSlides.setPower(0.5);
                    }
                }
            }
        }
    }
