// ITaskTimerAidlInterface.aidl
package rtrk.pnrs1.ra198_2013.taskmanager;

// Declare any non-default types here with import statements

interface ITaskTimerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void TaskAdded (String taskName);
    void TaskRemoved (String taskName);
    void TaskUpdated (String taskName);
}
