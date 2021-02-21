
package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.vision.UGRectDetector;

@TeleOp(name = "TeleOp", group = "UltimateGoal")
//@Disabled
public class UGTeleop extends LinearOpMode {

    DriveTrainStrafe robot = new DriveTrainStrafe();
    IntakeSubsystem intake = new IntakeSubsystem();
    ShooterSubsystem shooter = new ShooterSubsystem();
    ArmSubsystem arm = new ArmSubsystem();
    UGRectDetector vision = new UGRectDetector();
    double speed = 0;
    ElapsedTime runtime = new ElapsedTime();
    boolean buttonPrev [] = {false, false, false, false}; //bumper_left, bumper_right, gamepad2_y, dpad_up
    // TODO: fix those variable names
    //forward speed
    double ch3 = 0;
    //turn speed
    double ch1;
    //strafe speed
    double ch4 = 0;
    //are we shooting rings
    boolean tripleShoot = false;
    //manual or auto power adjustment
    boolean autoPower = true;
    boolean shooting = false;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap, this, "teleop");
        intake.init(hardwareMap, this, "teleop");
        shooter.init(hardwareMap, this, "teleop");
        vision.init(hardwareMap, "Webcam");
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // ### Control speed
            if (gamepad1.b) {
                speed = 0.3;
            } else if (gamepad1.a) {
                speed = 0.6;
            } else if (gamepad1.dpad_down) {
                speed = 0.15;
            }

            // ### both off
            if (gamepad2.a) {
                intake.turnOff();
                shooter.turnOff();
                shooting = false;
            }
            // ### intake on shooter off
            else if (gamepad2.x) {
                shooter.turnOff();
                intake.turnOn();
                shooting = false;
            }
            // ### intake off shooter on
            else if (gamepad2.b || shooting && !tripleShoot) {
                shooter.turnOn();
                intake.turnOff();
                shooting = true;
            }

            //triple shoot
            if (buttonClick(gamepad2.y, 2)) {
                tripleShoot = true;
                shooter.restartTime();
                //shooter.shooter.setPower(shooter.getPower()-0.06);
            }
            if (tripleShoot) {
                tripleShoot = shooter.shoot();
            } else {
                if (gamepad2.right_trigger > 0.6) {
                    shooter.trigger();
                } else {
                    shooter.neutral();
                }
            }
            if(buttonClick(gamepad2.dpad_up, 3)){
                autoPower = !autoPower;
            }
            /*
            if(autoPower && !tripleShoot){
                if(shooter.getBatteryVoltage()>13.9){
                    shooter.setPower(0.85);
                }
                else if(shooter.getBatteryVoltage()>13.4){
                    shooter.setPower(0.9);
                }
                else if(shooter.getBatteryVoltage()>12.9){
                    shooter.setPower(0.95);
                }
                else{
                    shooter.setPower(1);
                }
            }*/

            if (buttonClick(gamepad2.right_bumper, 1)) {
                shooter.setPower(shooter.getPower() + 0.05);
            }
            if (buttonClick(gamepad2.left_bumper, 0)) {
                shooter.setPower(shooter.getPower() - 0.05);
            }

            telemetry.addData("Rings", vision.getStack());
            telemetry.addData("Power", shooter.getPower());
            telemetry.addData("Voltage", shooter.getBatteryVoltage());
            telemetry.update();

            // ### Drive the robot
            ch3 = gamepad1.left_stick_y > 0.4 ? 0.9 : gamepad1.left_stick_y < -0.4 ? -0.9 : 0;
            ch4 = gamepad1.right_bumper ? -0.9 : gamepad1.left_bumper ? 0.9 : 0;
            ch1 = gamepad1.right_stick_x > 0.4 ? -0.6 : gamepad1.right_stick_x < -0.4 ? 0.6 : 0;
            robot.leftDriveFront.setPower((ch3 + ch1 + ch4) * speed);
            robot.leftDriveBack.setPower((ch3 + ch1 - ch4) * speed);
            robot.rightDriveFront.setPower((ch3 - ch1 - ch4) * speed);
            robot.rightDriveBack.setPower((ch3 - ch1 + ch4) * speed);
        }
    }

    public boolean buttonClick(boolean button, int loc) {
        boolean returnVal = button && !buttonPrev[loc];
        buttonPrev[loc] = button;
        return returnVal;
    }
}