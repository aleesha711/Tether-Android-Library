# Tether [![](https://jitpack.io/v/aleesha711/Tethering.svg)](https://jitpack.io/#aleesha711/Tethering)
Tether is an Android Library that tells you if Tethering is enabled on your device and list all the connected devices. It enables you to access all the necessary information you need i.e MAC, IP Address etc.

Tethering is the act of sharing your phone’s mobile data connection with another device such as your laptop or tablet — connecting it to the Internet through your phone’s data connection.

# Release
Available Version: [0.1.1](https://jitpack.io/#aleesha711/Tethering) on [jitpack.io](https://jitpack.io/#aleesha711/Tethering)

# Getting Started
**Prerequisites**

Minimum API Level is 18. Since this helper is based off Keystore API.

Adding the library
In your project level gradle, add:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
```

Add the dependency
```
dependencies {
	        implementation 'com.github.aleesha711:Tethering:0.1.1'
	}

```

# Usage

To know if Tehthering is enabled on device

```
Tether.isTetheringEnabled(this);

```
To know about connected devices and information

```

Tether.getTetheringClientsList(this);
 for (int i = 0; i < Tether.getTetheringClientsList(this).size(); i++) {
            Log.d("tetheringDeviceList", "ip : " + Tether.getTetheringClientsList(this).get(i).ipAddr + " " + "mac : " + Tether.getTetheringClientsList(this).get(i).hwAddr);
        }

```
# License

```
Copyright 2018 Aleesha Kanwal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
   
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
  - see the [LICENSE.md](https://github.com/aleesha711/Tethering/blob/master/LICENSE)
file for details.

# Author
[Aleesha Kanwal](https://www.linkedin.com/in/aleesha-kanwal-43b87671/)
