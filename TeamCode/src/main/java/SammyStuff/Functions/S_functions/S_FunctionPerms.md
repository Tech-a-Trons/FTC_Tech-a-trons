
# SammyFunctions functions

	~these are the same functions as SammyLinOp but SammyLinOp is not currently working~


| Functions           | Variables involved                              |
|---------------------|-------------------------------------------------|
| hardwareConfig      | `boolean useEncoder`                            |
| telemetryInit       | n/a                                             |
| telemetryAfterInit  | n/a                                             |
| MoveChassis         | n/a                                             |
| imuInit             | n/a                                             |
| SetTargetPosChassis | `int flt, int frt, int blt, int brt`            |
| setChassisPwr       | `double flp,double frp, double blp, double brp` |
| Movefsls(1&2)       | `int TrgetPos, double pwr`                      |
| will add more later |                                                 |




## **Perms**: *vedu(all)*















Copy and paste the stuff below to get access to 
all the commands(variables are commented out because I
dont know if you need them. so just in case)


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
private SammyFunctions functions = new SammyFunctions(this);


    // chassis motors
    //    DcMotor fl,fr,br,bl;
    //   //odometry
    //    DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,bme /* Back Middle encoder*/;
    //move chassis
    //    double drive,turn,strafe;
    //    double flpwr, frpwr, blpwr, brpwr;
    //    double flc,frc,blc,brc;
    //   //Imu
    //    IMU imu;
    //    Telemetry
    //    double YawAngle = functions.getYaw();
    //    double PitchAngle = functions.getPitch();
    //    double RollAngle = functions.getRoll();
    //    double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
    //    double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
    //    double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
    //   //servo
    //    Servo servo1;
    //    Servo servo2;
    //
    //    DcMotor fsls1;
    //    DcMotor fsls2;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

to call any command type:

`functions.(command that you want to enter)`