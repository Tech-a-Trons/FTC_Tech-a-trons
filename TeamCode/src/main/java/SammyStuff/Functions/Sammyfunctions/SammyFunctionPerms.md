
1.hardwareMap();


2.reverse();


3.use_encoder();


4.DontUseEncoders();


5.telemetryInit();


6.telemetryAfterInit();


7.brake();



8.teleOp();



9.imuInit();



10.SetTargetPosChassis(int flt, int frt, int blt, int brt);



11.setChassisPwr(double flp,double frp, double blp, double brp);



12.Movefsls(1&2)TargetPos(int TargetPos, double pwr)








will add more later








Perms to use: vedu(all)


Copy and paste the stuff below to get access to all the commands
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 private SammyFunctions functions= new SammyFunctions(this);
    DcMotor fl;
    DcMotor fr;
    DcMotor br;
    DcMotor bl;
    DcMotor fle, fre,bme;
    double drive;
    double turn;
    double strafe;
    double flpwr, frpwr, blpwr, brpwr;
    double flc,frc,blc,brc;
    IMU imu;
    double YawAngle = getYaw();
    double PitchAngle = getPitch();
    double RollAngle = getRoll();

    double FLOV = fle.getCurrentPosition();
    double FROV = fre.getCurrentPosition();
    double BMOV = bme.getCurrentPosition();
    Servo servo1;
    Servo servo2;
    DcMotor fsls_1;
    DcMotor fsls_2;