1.hardwareMap(boolean useEncoder);


2.reverse();



3.telemetryInit();


4.telemetryAfterInit();


5.brake();



6.teleOp();



7.imuInit();



8.SetTargetPosChassis(int flt, int frt, int blt, int brt);



9.setChassisPwr(double flp,double frp, double blp, double brp);



10.Movefsls(1&2)TargetPos(int TargetPos, double pwr);




will add more later










Perms to use: vedu(all)









This is the same functions as SammyLinOp, but SammyLinOp isnt working rn







Copy and paste the stuff below to get access to all the commands
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
private SammyFunctions functions = new SammyFunctions(this);


 //   DcMotor fl;
 //   DcMotor fr;
 //   DcMotor br;
 //   DcMotor bl;
 //   DcMotor fle, fre,bme;
 //   double drive;
 //   double turn;
 //   double strafe;
 //   double flpwr, frpwr, blpwr, brpwr;
 //   double flc,frc,blc,brc;
 //   IMU imu;
 //   double YawAngle = getYaw();
 //   double PitchAngle = getPitch();
 //   double RollAngle = getRoll();
 //   double FLOV = functions.fle.getCurrentPosition();
 //   double FROV = functions.fre.getCurrentPosition();
 //   double BMOV = functions.bme.getCurrentPosition();
 //  Servo servo1;
 //   Servo servo2;
 //   DcMotor fsls1;
 //   DcMotor fsls2;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
