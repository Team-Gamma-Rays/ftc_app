/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * ADDED BY GAMMA RAYS:
 * This OpMode mode is based upon the PushbotTeleopPOV_Linear class.
 */

/**
 * ORIGINAL COMMENT:
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Claw Servo Test", group="Testing")
//@Disabled
public class TwoServoClawTest extends LinearOpMode {

    Servo leftClaw  = null;
    Servo rightClaw = null;

    static final double LEFT_CLAW_HOME  = 0.89; //Set starting position for servos
    static final double RIGHT_CLAW_HOME = 0.02;
    static final double SERVO_DELTA     = 0.01; //Set amount to move servos

    @Override
    public void runOpMode() {

        leftClaw  = hardwareMap.get(Servo.class, "left_claw");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");

        double leftClawPos; //Define variables to store positions of servos.
        double rightClawPos;

        leftClaw.setPosition(LEFT_CLAW_HOME);
        rightClaw.setPosition(RIGHT_CLAW_HOME);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Assigns servo positions to variables.
            leftClawPos  = leftClaw.getPosition();
            rightClawPos = rightClaw.getPosition();

            if (gamepad1.y)
                leftClaw.setPosition(leftClawPos - SERVO_DELTA);
            else if (gamepad1.a)
                leftClaw.setPosition(leftClawPos + SERVO_DELTA);

            if (gamepad1.x)
                rightClaw.setPosition(rightClawPos - SERVO_DELTA);
            else if (gamepad1.b)
                rightClaw.setPosition(rightClawPos + SERVO_DELTA);

            //Send telemetry messages corresponding to servo positions.
            telemetry.addData("leftClaw:", "%.2f", leftClawPos);
            telemetry.addData("rightClaw:", "%.2f", rightClawPos);
            telemetry.update();
        }
    }
}
