package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeSubsystem {
    LinearOpMode op_mode = null;
    public DcMotor m0 = null;   // upper motor driving Geko wheels
    public DcMotor m1 = null;   // lower motor driving disc wheels

    HardwareMap hwMap = null;
    ElapsedTime runtime = new ElapsedTime();

    public void init(HardwareMap ahwMap, LinearOpMode opMode, String runeMode) {
        op_mode = opMode;
        hwMap = ahwMap;

        m0 = hwMap.get(DcMotor.class, "intake_m0");
        m1 = hwMap.get(DcMotor.class, "intake_m1");

        m0.setDirection(DcMotor.Direction.REVERSE);
        m1.setDirection(DcMotor.Direction.REVERSE);
    }

    public void turnOn(){
        m0.setPower(0.8);
        m1.setPower(0.8);
    }

    public void turnOff() {
        m0.setPower(0);
        m1.setPower(0);
    }
}
