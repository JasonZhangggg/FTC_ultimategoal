package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ShooterSubsystem {
    final int reloadTime = 300;
    LinearOpMode op_mode = null;
    public DcMotor shooter = null;   // shooter motor
    public Servo pusher = null;
    int count = 0;
    HardwareMap hwMap = null;
    double power = 0.9;
    public ElapsedTime runtime = new ElapsedTime();

    public void init(HardwareMap ahwMap, LinearOpMode opMode, String runeMode) {
        op_mode = opMode;
        hwMap = ahwMap;
        shooter = hwMap.get(DcMotor.class, "shooter");
        pusher = hwMap.get(Servo.class, "servo");
        shooter.setDirection(DcMotor.Direction.REVERSE);
    }

    public void turnOn() {
        shooter.setPower(power);
    }

    public void turnOff() {
        shooter.setPower(0);
    }

    public void trigger() {
        pusher.setPosition(0.9);
    }

    public boolean shoot() {
        double mil = runtime.milliseconds();
        if (count == 3) {
            count=0;
            return false;
        }
        if (mil > 0 && mil < 200) trigger();
        if (mil > 200 && mil < 400){
            neutral();
            turnOn();
        }
        if (mil > 400) {
            restartTime();
            count++;
        }
        return true;
    }

    public void neutral() {
        pusher.setPosition(0.7);
    }

    public void restartTime() {
        runtime.reset();
        runtime.startTime();
    }
    public void setPower(double p){
         power = p;
    }
    public double getPower(){
        return power;
    }
    double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hwMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }
}
