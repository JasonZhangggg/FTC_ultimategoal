package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.vision.UGRectDetector;

@TeleOp(name = "DetectRings2", group = "UltimateGoal")
//@Disabled
public class DetectStack extends LinearOpMode {

    UGRectDetector vision = new UGRectDetector();
    @Override
    public void runOpMode() {

        vision.init(hardwareMap, "noWebCam");
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Rings", vision.getStack());
            telemetry.update();
        }
    }
}