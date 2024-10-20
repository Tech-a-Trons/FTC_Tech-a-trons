package SammyStuff.SammyOpVersions.Interfaces;

import com.qualcomm.robotcore.hardware.Gamepad;

public interface LinOpDefault {
    /**
     * LinearOpMode constructor
     */
    void LinearOpMode();

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not catch {@link InterruptedException}s that are thrown in your OpMode
     * unless you are doing it to perform some brief cleanup, in which case you must exit
     * immediately afterward. Once the OpMode has been told to stop, your ability to
     * control hardware will be limited.
     *
     * @throws InterruptedException When the OpMode is stopped while calling a method
     *                              that can throw {@link InterruptedException}
     */
    void runOpMode() throws InterruptedException;

    /**
     * Pauses until the play button has been pressed (or until the current thread
     * gets interrupted, which typically indicates that the OpMode has been stopped).
     */
    void waitForStart();

    /**
     * Puts the current thread to sleep for a bit as it has nothing better to do. This allows other
     * threads in the system to run.
     *
     * <p>One can use this method when you have nothing better to do in your code as you await state
     * managed by other threads to change. Calling Idle() is entirely optional: it just helps make
     * the system a little more responsive and a little more efficient.</p>
     *
     * @see #opModeIsActive()
     */
    void idle();

    /**
     * Sleeps for the given amount of milliseconds, or until the thread is interrupted (which usually
     * indicates that the OpMode has been stopped).
     * <p>This is simple shorthand for {@link Thread#sleep(long) sleep()}, but it does not throw {@link InterruptedException}.</p>
     *
     * @param milliseconds amount of time to sleep, in milliseconds
     * @see Thread#sleep(long)
     */
    void Sleep(long milliseconds);

    /**
     * Determine whether this OpMode is in the Run phase (meaning it has been started and not yet told
     * to stop). For safety reasons, the Run phase is the only time that the robot should move freely,
     * so if this method returns {@code false}, the robot should not make any significant movements.
     *
     * <p>If this method returns false after {@link #waitForStart()} has previously been called, you
     * should break out of any loops and allow the OpMode to exit at its earliest convenience.</p>
     *
     * <p>Note that this method calls {@link #idle()} internally.</p>
     *
     * @return Whether the OpMode is in the Run phase and the robot is allowed to move freely.
     * @see #runOpMode()
     * @see #isStarted()
     * @see #isStopRequested()
     */
    boolean opModeIsActive();

    /**
     * Determine whether this OpMode is still in the Init phase (indicating that the play button has
     * not been pressed and the OpMode has not been stopped).
     * <p>
     * Can be used to break out of an Init loop when false is returned.
     *
     * @return Whether the OpMode is currently in the Init phase.
     */
    boolean opModeInInit();

    boolean isStarted();

    /**
     * Determine whether the OpMode has been asked to stop.
     *
     * <p>If this method returns false, you should break out of any loops
     * and allow the OpMode to exit at its earliest convenience.</p>
     *
     * @return Whether the OpMode has been asked to stop
     * @see #opModeIsActive()
     * @see #isStarted()
     */
    boolean isStopRequested();

    /**
     * This method may not be overridden by linear OpModes
     */
    void init();

    /**
     * This method may not be overridden by linear OpModes
     */
    void init_loop();

    /**
     * This method may not be overridden by linear OpModes
     */
    void start();

    /**
     * This method may not be overridden by linear OpModes
     */
    void loop();

    /**
     * This method may not be overridden by linear OpModes
     */
    void stop();

    // Package-private, called on the OpModeThread when the OpMode is initialized
    void internalRunOpMode() throws InterruptedException;

    // Package-private, called on the main event loop thread
    void internalOnStart();

    // Package-private, called on the main event loop thread
    void internalOnEventLoopIteration();

    // Package-private, called on the main event loop thread
    void internalOnStopRequested();

    void newGamepadDataAvailable(Gamepad latestGamepad1Data, Gamepad latestGamepad2Data);
}
