# Kava Touch

Kava Touch is an alternative application framework for Android. It is modeled closely after the Cocoa Touch framework, which runs on the iPhone and iPad. The programming language is Java.

Kava Touch aims at providing a complete replacement for all Android classes that are not part of the Java standard. The current focus is on GUI classes and threading, but file management and networking will eventually be covered.

The Kava Touch framework is dual-licensed under Apache 2.0 and MIT terms.

See [COPYRIGHT], [LICENSE-APACHE] and [LICENSE-MIT] for details. See [AUTHORS] for a list of contributors.

## News

*June 5, 2013: The initial version of Kava Touch was released and is available on GitHub!*

## Quick Start

For now, see the sample projects in [KavaTouchSamples](https://github.com/KavaProject/KavaTouchSamples).

## Architecture

The current implementation runs in a separate thread and renders to a custom *SurfaceView*. Applications programmed with Kava Touch typically contain one Android *Activity* that sets up this *SurfaceView* with an application delegate. The delegate will be used to initialize the Kava Touch environment upon loading. Kava Touch *UIView*s reuse the drawing logic of their Android equivalent to achieve the look of native Android applications.

## Target Audience

Kava Touch will (once version 1.0 is reached) be a good alternative for programmers who are new to the Android platform and want to profit from an extensively documented framework (which Cocoa Touch is), based on a tried and proven API. It could also be of interest for developers who want to port their existing Cocoa and Cocoa Touch applications to Android devices, while staying close to the applications original design/architecture.

## Building

Building requires an Android platform version 17 SDK, the minimum runtime platform version for deployment is version 10. To run the instrumentation tests using Gradle, make sure an Android device is connected and issue gradle's connectedInstrumentTest command (Replace the device IP and the Android SDK install location):
```bash
adb connect 192.168.0.200
export ANDROID_HOME=/Applications/android-sdk-macosx/; ./gradlew connectedInstrumentTest
```
You can also open the repository as an IntelliJ IDEA project, or import it into Eclipse.

## Contributing

Pull requests are welcome. If you find bugs or want certain features to be prioritized, please open an issue using the [bug tracker](http://kava.myjetbrains.com/youtrack/issues/KT).

## Getting Help

The [tutorial](https://github.com/KavaProject/KavaTouch/wiki/tutorial) (TODO) and the [wiki](https://github.com/KavaProject/KavaTouch/wiki) are good starting points. See also http://www.kavaproject.org/ for general information about projects that use or extend Kava Touch.

If you have questions, post a message to the [forum](http://kava-touch.14305.n7.nabble.com).
