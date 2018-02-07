package org.usfirst.frc.team4661.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4661.robot.commands.vision.Capture;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {

	public UsbCamera cam;
	private CvSink sink;
	private CvSource output;

	public Vision(UsbCamera cam) {
		this.cam = cam;
		cam.setResolution(640,480);
		sink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard

		output = CameraServer.getInstance().putVideo("image", 640, 480);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new Capture());
	}

	public void doIteration() {
		// CR make mat a field and move the initialization to the c'tor.
		Mat mat = new Mat();

		if (sink.grabFrame(mat) == 0) {
			// Send the output the error.
			output.notifyError(sink.getError());
			// skip the rest of the current iteration
			return;
		}
		
		// CR remove these two lines. We don't need this anymore.
		String str = ""+ Timer.getFPGATimestamp();
		Imgproc.putText(mat, str, new Point(320,240), 0,3, new Scalar(0,0,255)); 
		
		// Give the output stream a new image to display
		output.putFrame(mat);
		
	}
}
