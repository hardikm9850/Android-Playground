// IAIDLInterface.aidl
package com.hardik.playground;

// Declare any non-default types here with import statements

interface IAIDLInterface {

              /** Request the process ID of this service */
                int getPid();

                /** Count of received connection requests from clients */
                int getConnectionCount();

                /** Set displayed value of screen */
                void setDisplayedValue(String packageName, int pid, String data);
}