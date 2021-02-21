package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmSubsystem {
    LinearOpMode op_mode = null;
    public DcMotor arm = null;   // arm motor

    // TODO: need 2 touch sensors to control the degree of rotation

    HardwareMap hwMap = null;
    ElapsedTime runtime = new ElapsedTime();

    public void init(HardwareMap ahwMap, LinearOpMode opMode, String runeMode) {
        op_mode = opMode;
        hwMap = ahwMap;

        arm = hwMap.get(DcMotor.class, "arm");
    }

    public void rotateForward(){

    }

    public void rotateBackward(){

    }
}
