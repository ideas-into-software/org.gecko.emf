# org.geckoprojects.mongo.core

## Links

* [Documentation](https://github.com/geckoprojects-org/org.geckoprojects.mongo)
* [Source Code](https://github.com/geckoprojects-org/org.geckoprojects.mongo) (clone with `scm:git:git@github.com:geckoprojects-org/org.geckoprojects.mongo.git`)

## Coordinates

### Maven

```xml
<dependency>
    <groupId>org.geckoprojects</groupId>
    <artifactId>org.geckoprojects.mongo.core</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### OSGi

```
Bundle Symbolic Name: org.geckoprojects.mongo.core
Version             : 0.0.1.SNAPSHOT
```

### Feature-Coordinate

```
"bundles": [
   {
    "id": "org.geckoprojects:org.geckoprojects.mongo.core:0.0.1-SNAPSHOT"
   }
]
```

## Components

### org.geckoprojects.mongo.core.impl.MongoClientSync - *state = enabled, activation = delayed*

#### Description

#### Services - *scope = singleton*

|Interface name |
|--- |
|com.mongodb.client.MongoClient |

#### Properties

|Name |Type |Value |
|--- |--- |--- |
|heartbeatFrequency |Integer |-1 |
|minHeartbeatFrequency |Long |-1 |
|localThreshold |Integer |-1 |
|serverSelectionTimeout |Integer |-1 |
|maintenanceFrequency |Integer |-1 |
|maintenanceInitialDelay |Long |-1 |
|maxConnectionIdleTime |Long |-1 |
|maxConnectionLifeTime |Long |-1 |
|maxSize |Integer |-1 |
|maxWaitTime |Long |-2 |
|minSize |Integer |-1 |
|connectTimeout |Integer |-1 |
|readTimeout |Integer |-1 |
|receiveBufferSize |Integer |-1 |
|sendBufferSize |Integer |-1 |
|sslEnabled |Boolean |false |
|invalidHostNameAllowed |Boolean |false |
|retryWrites |Boolean |true |

#### Configuration - *policy = require*

##### Factory Pid: `org.geckoprojects.mongo.core.MongoClient`

|Attribute |Value |
|--- |--- |
|Id |`client_id` |
|Required |**true** |
|Type |**String** |

|Attribute |Value |
|--- |--- |
|Id |`databaseNames` |
|Required |**false** |
|Type |**String[]** |

|Attribute |Value |
|--- |--- |
|Id |`credential.password` |
|Required |**false** |
|Type |**Char[]** |

|Attribute |Value |
|--- |--- |
|Id |`credential.source` |
|Required |**false** |
|Type |**String** |

|Attribute |Value |
|--- |--- |
|Id |`credential.type` |
|Required |**false** |
|Type |**String** |
|Value range |"GSSAPI", "PLAIN", "MONGODB_X509", "MONGODB_CR", "SCRAM_SHA_1", "SCRAM_SHA_256" |

|Attribute |Value |
|--- |--- |
|Id |`credential.username` |
|Required |**false** |
|Type |**String** |

|Attribute |Value |
|--- |--- |
|Id |`driverName` |
|Required |**false** |
|Type |**String** |
|Description |Sets the name |

|Attribute |Value |
|--- |--- |
|Id |`driverPlatform` |
|Required |**false** |
|Type |**String** |
|Description |Sets the platform |

|Attribute |Value |
|--- |--- |
|Id |`driverVersion` |
|Required |**false** |
|Type |**String** |
|Description |Sets the version - Note: You must also set a driver name if setting a driver version. |

|Attribute |Value |
|--- |--- |
|Id |`serverAdresses` |
|Required |**false** |
|Type |**String[]** |
|Description |Sets the hosts for the cluster. Any duplicate server addresses are removed from the list. ServerAdresses in the pattern `host` or `host:port`. If not set the default Host and Port will be used. |

|Attribute |Value |
|--- |--- |
|Id |`heartbeatFrequency` |
|Required |**false** |
|Type |**Integer** |
|Description |Sets the frequency that the cluster monitor attempts to reach each server. The Mongo-default value is 10 seconds. Time in millis, if <=0 value will be ignored |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`minHeartbeatFrequency` |
|Required |**false** |
|Type |**Long** |
|Description |Sets the minimum heartbeat frequency. In the event that the driver has to frequently re-check a server's availability, it will wait at least this long since the previous check to avoid wasted effort.  The Mongo-default value is 500 milliseconds. Time in millis, if <=0 value will be ignored |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`applicationName` |
|Required |**false** |
|Type |**String** |
|Description |The logical name of the application using this MongoClient. It may be null. The application name may be used by the client to identify the application to the server, for use in server logs, slow query logs, and profile collection. The UTF-8 encoding may not exceed 128 bytes. |

|Attribute |Value |
|--- |--- |
|Id |`localThreshold` |
|Required |**false** |
|Type |**Integer** |
|Description |localThreshold the acceptable latency difference, in milliseconds, which must be >= 0. Mongo-default 15 ms |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`clusterConnectionMode` |
|Required |**false** |
|Type |**String** |
|Description |Sets the mode for this cluster. |
|Value range |"SINGLE", "MULTIPLE" |

|Attribute |Value |
|--- |--- |
|Id |`requiredClusterType` |
|Required |**false** |
|Type |**String** |
|Description |Sets the required cluster type for the cluster. |
|Value range |"STANDALONE", "REPLICA_SET", "SHARDED", "UNKNOWN" |

|Attribute |Value |
|--- |--- |
|Id |`requiredReplicaSetName` |
|Required |**false** |
|Type |**String** |
|Description |Sets the required replica set name for the cluster. |

|Attribute |Value |
|--- |--- |
|Id |`serverSelectionTimeout` |
|Required |**false** |
|Type |**Integer** |
|Description |Sets the timeout to apply when selecting a server.  If the timeout expires before a server is found to handle a request, a com.mongodb.MongoTimeoutException will be thrown.  The default value is 30 seconds. A value of 0 means that it will timeout immediately if no server is available. A negative value means to wait indefinitely. |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`srvHost` |
|Required |**false** |
|Type |**String** |
|Description |Sets the host name to use in order to look up an SRV DNS record to find the MongoDB hosts. Note that when setting srvHost via {@code ClusterSettings.Builder}, the driver will NOT process any associated TXT records associated with the host.  In order to enable the processing of TXT records while still using {@code MongoClientSettings}, specify the SRV host via connection string and apply the connection string to the settings, e.g. {@code MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb+srv://host1.acme.com")) |

|Attribute |Value |
|--- |--- |
|Id |`maintenanceFrequency` |
|Required |**false** |
|Type |**Integer** |
|Description |The time period between runs of the maintenance job. In millis >0. Mongo-default 60 seconds |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`maintenanceInitialDelay` |
|Required |**false** |
|Type |**Long** |
|Description |The period of time to wait before running the first maintenance job on the connection pool. In millis >=0. Mongo-default 0 millis |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`maxConnectionIdleTime` |
|Required |**false** |
|Type |**Long** |
|Description |The maximum idle time of a pooled connection.  A zero value indicates no limit to the idle time.  A pooled connection that has exceeded its idle time will be closed and replaced when necessary by a new connection. In millis >=0. Mongo-default 60 millis |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`maxConnectionLifeTime` |
|Required |**false** |
|Type |**Long** |
|Description |The maximum time a pooled connection can live for.  A zero value indicates no limit to the life time.  A pooled connection that has exceeded its life time will be closed and replaced when necessary by a new connection. In millis >=0. Mongo-default 60 millis |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`maxSize` |
|Required |**false** |
|Type |**Integer** |
|Description |The maximum number of connections allowed. Those connections will be kept in the pool when idle. Once the pool is exhausted, any operation requiring a connection will block waiting for an available connection. In millis >0. Mongo-default 100 |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`maxWaitTime` |
|Required |**false** |
|Type |**Long** |
|Description |The maximum time that a thread may wait for a connection to become available. Default is 2 minutes. A value of 0 means that it will not wait.  A -1 value means it will wait indefinitely. In millis >0. Mongo-default 2 x 60 x 1000 millis |
|Default |-2 |

|Attribute |Value |
|--- |--- |
|Id |`minSize` |
|Required |**false** |
|Type |**Integer** |
|Description |The minimum number of connections. Those connections will be kept in the pool when idle, and the pool will ensure that it contains at least this minimum number. In millis >=0. Mongo-default 0 |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`connectTimeout` |
|Required |**false** |
|Type |**Integer** |
|Description |Sets the socket connect timeout. Mongo-default 10.000 |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`readTimeout` |
|Required |**false** |
|Type |**Integer** |
|Description |Sets the socket read timeout. |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`receiveBufferSize` |
|Required |**false** |
|Type |**Integer** |
|Description |Sets the receive buffer size. |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`sendBufferSize` |
|Required |**false** |
|Type |**Integer** |
|Description |Sets the receive buffer size |
|Default |-1 |

|Attribute |Value |
|--- |--- |
|Id |`sslProtocol` |
|Required |**false** |
|Type |**String** |
|Description |Sets the Protocol for SSLContext for use when SSL is enabled. |

|Attribute |Value |
|--- |--- |
|Id |`sslProvider` |
|Required |**false** |
|Type |**String** |
|Description |Sets the Provicer for SSLContext for use when SSL is enabled. |

|Attribute |Value |
|--- |--- |
|Id |`sslEnabled` |
|Required |**false** |
|Type |**Boolean** |
|Description |Define whether invalid host names should be allowed.  Defaults to false.  Take care before setting this to true, as it makes the application susceptible to man-in-the-middle attacks. |
|Default |false |

|Attribute |Value |
|--- |--- |
|Id |`invalidHostNameAllowed` |
|Required |**false** |
|Type |**Boolean** |
|Description |Define whether invalid host names should be allowed.  Defaults to false.  Take care before setting this to true, as it makes the application susceptible to man-in-the-middle attacks. |
|Default |false |

|Attribute |Value |
|--- |--- |
|Id |`readConcern` |
|Required |**false** |
|Type |**String** |
|Description |Sets the read concern |
|Value range |"LOCAL", "MAJORITY", "LINEARIZABLE", "SNAPSHOT", "AVAILABLE" |

|Attribute |Value |
|--- |--- |
|Id |`retryWrites` |
|Required |**false** |
|Type |**Boolean** |
|Description |Sets whether writes should be retried if they fail due to a network error. Starting with the 3.11.0 release, the default value is true |
|Default |true |

|Attribute |Value |
|--- |--- |
|Id |`uuidRepresentation` |
|Required |**false** |
|Type |**String** |
|Description |Sets the UUID representation to use when encoding instances of {@link java.util.UUID} and when decoding BSON binary values with subtype of 3. See {@link #getUuidRepresentation()} for recommendations on settings this value. |
|Value range |"UNSPECIFIED", "STANDARD", "C_SHARP_LEGACY", "JAVA_LEGACY", "PYTHON_LEGACY" |

#### Reference bindings

|Attribute |Value |
|--- |--- |
|name |autoEncryptionSettings |
|interfaceName |com.mongodb.AutoEncryptionSettings |
|target | |
|cardinality |0..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |clusterListeners |
|interfaceName |com.mongodb.event.ClusterListener |
|target | |
|cardinality |0..n |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |codecRegistry |
|interfaceName |org.bson.codecs.configuration.CodecRegistry |
|target | |
|cardinality |0..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |commandListeners |
|interfaceName |com.mongodb.event.CommandListener |
|target | |
|cardinality |0..n |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |compressorList |
|interfaceName |com.mongodb.MongoCompressor |
|target | |
|cardinality |0..n |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |connectionPoolListeners |
|interfaceName |com.mongodb.event.ConnectionPoolListener |
|target | |
|cardinality |0..n |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |dbDecoderFactory |
|interfaceName |com.mongodb.DBDecoderFactory |
|target | |
|cardinality |0..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |dbEncoderFactory |
|interfaceName |com.mongodb.DBEncoderFactory |
|target | |
|cardinality |0..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |serverListeners |
|interfaceName |com.mongodb.event.ServerListener |
|target | |
|cardinality |0..n |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |serverMonitorListeners |
|interfaceName |com.mongodb.event.ServerMonitorListener |
|target | |
|cardinality |0..n |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |serverSelector |
|interfaceName |com.mongodb.selector.ServerSelector |
|target | |
|cardinality |0..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle ||Attribute |Value |
|--- |--- |
|name |streamFactoryFactory |
|interfaceName |com.mongodb.connection.StreamFactoryFactory |
|target | |
|cardinality |0..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle |

#### OSGi-Configurator


```
/*
 * Component: org.geckoprojects.mongo.core.impl.MongoClientSync
 * policy:    require
 */
"org.geckoprojects.mongo.core.MongoClient~FactoryNameChangeIt":{
        //# Component properties
        /*
         * Type = Integer
         * Default = -1
         */
         // "heartbeatFrequency": null,

        /*
         * Type = Long
         * Default = -1
         */
         // "minHeartbeatFrequency": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "localThreshold": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "serverSelectionTimeout": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "maintenanceFrequency": null,

        /*
         * Type = Long
         * Default = -1
         */
         // "maintenanceInitialDelay": null,

        /*
         * Type = Long
         * Default = -1
         */
         // "maxConnectionIdleTime": null,

        /*
         * Type = Long
         * Default = -1
         */
         // "maxConnectionLifeTime": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "maxSize": null,

        /*
         * Type = Long
         * Default = -2
         */
         // "maxWaitTime": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "minSize": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "connectTimeout": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "readTimeout": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "receiveBufferSize": null,

        /*
         * Type = Integer
         * Default = -1
         */
         // "sendBufferSize": null,

        /*
         * Type = Boolean
         * Default = false
         */
         // "sslEnabled": null,

        /*
         * Type = Boolean
         * Default = false
         */
         // "invalidHostNameAllowed": null,

        /*
         * Type = Boolean
         * Default = true
         */
         // "retryWrites": null,


        //# Reference bindings
        // "autoEncryptionSettings.target": "(component.pid=*)",
        // "clusterListeners.target": "(component.pid=*)",
        // "codecRegistry.target": "(component.pid=*)",
        // "commandListeners.target": "(component.pid=*)",
        // "compressorList.target": "(component.pid=*)",
        // "connectionPoolListeners.target": "(component.pid=*)",
        // "dbDecoderFactory.target": "(component.pid=*)",
        // "dbEncoderFactory.target": "(component.pid=*)",
        // "serverListeners.target": "(component.pid=*)",
        // "serverMonitorListeners.target": "(component.pid=*)",
        // "serverSelector.target": "(component.pid=*)",
        // "streamFactoryFactory.target": "(component.pid=*)",


        //# ObjectClassDefinition - Attributes
        /*
         * Required = true
         * Type = String
         */
         "client_id": null,

        /*
         * Required = false
         * Type = String[]
         */
         // "databaseNames": null,

        /*
         * Required = false
         * Type = Char[]
         */
         // "credential.password": null,

        /*
         * Required = false
         * Type = String
         */
         // "credential.source": null,

        /*
         * Required = false
         * Type = String
         * Value restriction = "GSSAPI", "PLAIN", "MONGODB_X509", "MONGODB_CR", "SCRAM_SHA_1", "SCRAM_SHA_256"
         */
         // "credential.type": null,

        /*
         * Required = false
         * Type = String
         */
         // "credential.username": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the name
         */
         // "driverName": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the platform
         */
         // "driverPlatform": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the version - Note: You must also set a driver name if setting a driver version.
         */
         // "driverVersion": null,

        /*
         * Required = false
         * Type = String[]
         * Description = Sets the hosts for the cluster. Any duplicate server addresses are removed from the list. ServerAdresses in the pattern `host` or `host:port`. If not set the default Host and Port will be used.
         */
         // "serverAdresses": null,

        /*
         * Required = false
         * Type = Integer
         * Description = Sets the frequency that the cluster monitor attempts to reach each server. The Mongo-default value is 10 seconds. Time in millis, if <=0 value will be ignored
         * Default = -1
         */
         // "heartbeatFrequency": null,

        /*
         * Required = false
         * Type = Long
         * Description = Sets the minimum heartbeat frequency. In the event that the driver has to frequently re-check a server's availability, it will wait at least this long since the previous check to avoid wasted effort.  The Mongo-default value is 500 milliseconds. Time in millis, if <=0 value will be ignored
         * Default = -1
         */
         // "minHeartbeatFrequency": null,

        /*
         * Required = false
         * Type = String
         * Description = The logical name of the application using this MongoClient. It may be null. The application name may be used by the client to identify the application to the server, for use in server logs, slow query logs, and profile collection. The UTF-8 encoding may not exceed 128 bytes.
         */
         // "applicationName": null,

        /*
         * Required = false
         * Type = Integer
         * Description = localThreshold the acceptable latency difference, in milliseconds, which must be >= 0. Mongo-default 15 ms
         * Default = -1
         */
         // "localThreshold": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the mode for this cluster.
         * Value restriction = "SINGLE", "MULTIPLE"
         */
         // "clusterConnectionMode": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the required cluster type for the cluster.
         * Value restriction = "STANDALONE", "REPLICA_SET", "SHARDED", "UNKNOWN"
         */
         // "requiredClusterType": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the required replica set name for the cluster.
         */
         // "requiredReplicaSetName": null,

        /*
         * Required = false
         * Type = Integer
         * Description = Sets the timeout to apply when selecting a server.  If the timeout expires before a server is found to handle a request, a com.mongodb.MongoTimeoutException will be thrown.  The default value is 30 seconds. A value of 0 means that it will timeout immediately if no server is available. A negative value means to wait indefinitely.
         * Default = -1
         */
         // "serverSelectionTimeout": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the host name to use in order to look up an SRV DNS record to find the MongoDB hosts. Note that when setting srvHost via {@code ClusterSettings.Builder}, the driver will NOT process any associated TXT records associated with the host.  In order to enable the processing of TXT records while still using {@code MongoClientSettings}, specify the SRV host via connection string and apply the connection string to the settings, e.g. {@code MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb+srv://host1.acme.com"))
         */
         // "srvHost": null,

        /*
         * Required = false
         * Type = Integer
         * Description = The time period between runs of the maintenance job. In millis >0. Mongo-default 60 seconds
         * Default = -1
         */
         // "maintenanceFrequency": null,

        /*
         * Required = false
         * Type = Long
         * Description = The period of time to wait before running the first maintenance job on the connection pool. In millis >=0. Mongo-default 0 millis
         * Default = -1
         */
         // "maintenanceInitialDelay": null,

        /*
         * Required = false
         * Type = Long
         * Description = The maximum idle time of a pooled connection.  A zero value indicates no limit to the idle time.  A pooled connection that has exceeded its idle time will be closed and replaced when necessary by a new connection. In millis >=0. Mongo-default 60 millis
         * Default = -1
         */
         // "maxConnectionIdleTime": null,

        /*
         * Required = false
         * Type = Long
         * Description = The maximum time a pooled connection can live for.  A zero value indicates no limit to the life time.  A pooled connection that has exceeded its life time will be closed and replaced when necessary by a new connection. In millis >=0. Mongo-default 60 millis
         * Default = -1
         */
         // "maxConnectionLifeTime": null,

        /*
         * Required = false
         * Type = Integer
         * Description = The maximum number of connections allowed. Those connections will be kept in the pool when idle. Once the pool is exhausted, any operation requiring a connection will block waiting for an available connection. In millis >0. Mongo-default 100
         * Default = -1
         */
         // "maxSize": null,

        /*
         * Required = false
         * Type = Long
         * Description = The maximum time that a thread may wait for a connection to become available. Default is 2 minutes. A value of 0 means that it will not wait.  A -1 value means it will wait indefinitely. In millis >0. Mongo-default 2 x 60 x 1000 millis
         * Default = -2
         */
         // "maxWaitTime": null,

        /*
         * Required = false
         * Type = Integer
         * Description = The minimum number of connections. Those connections will be kept in the pool when idle, and the pool will ensure that it contains at least this minimum number. In millis >=0. Mongo-default 0
         * Default = -1
         */
         // "minSize": null,

        /*
         * Required = false
         * Type = Integer
         * Description = Sets the socket connect timeout. Mongo-default 10.000
         * Default = -1
         */
         // "connectTimeout": null,

        /*
         * Required = false
         * Type = Integer
         * Description = Sets the socket read timeout.
         * Default = -1
         */
         // "readTimeout": null,

        /*
         * Required = false
         * Type = Integer
         * Description = Sets the receive buffer size.
         * Default = -1
         */
         // "receiveBufferSize": null,

        /*
         * Required = false
         * Type = Integer
         * Description = Sets the receive buffer size
         * Default = -1
         */
         // "sendBufferSize": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the Protocol for SSLContext for use when SSL is enabled.
         */
         // "sslProtocol": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the Provicer for SSLContext for use when SSL is enabled.
         */
         // "sslProvider": null,

        /*
         * Required = false
         * Type = Boolean
         * Description = Define whether invalid host names should be allowed.  Defaults to false.  Take care before setting this to true, as it makes the application susceptible to man-in-the-middle attacks.
         * Default = false
         */
         // "sslEnabled": null,

        /*
         * Required = false
         * Type = Boolean
         * Description = Define whether invalid host names should be allowed.  Defaults to false.  Take care before setting this to true, as it makes the application susceptible to man-in-the-middle attacks.
         * Default = false
         */
         // "invalidHostNameAllowed": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the read concern
         * Value restriction = "LOCAL", "MAJORITY", "LINEARIZABLE", "SNAPSHOT", "AVAILABLE"
         */
         // "readConcern": null,

        /*
         * Required = false
         * Type = Boolean
         * Description = Sets whether writes should be retried if they fail due to a network error. Starting with the 3.11.0 release, the default value is true
         * Default = true
         */
         // "retryWrites": null,

        /*
         * Required = false
         * Type = String
         * Description = Sets the UUID representation to use when encoding instances of {@link java.util.UUID} and when decoding BSON binary values with subtype of 3. See {@link #getUuidRepresentation()} for recommendations on settings this value.
         * Value restriction = "UNSPECIFIED", "STANDARD", "C_SHARP_LEGACY", "JAVA_LEGACY", "PYTHON_LEGACY"
         */
         // "uuidRepresentation": null
}
```

## Developers

* **Juergen Albert** (jalbert) / [j.albert@data-in-motion.biz](mailto:j.albert@data-in-motion.biz) @ [Data In Motion](https://www.datainmotion.de) - *architect*, *developer*
* **Mark Hoffmann** (mhoffmann) / [m.hoffmann@data-in-motion.biz](mailto:m.hoffmann@data-in-motion.biz) @ [Data In Motion](https://www.datainmotion.de) - *developer*, *architect*

## Licenses

**Apache License 2.0**

## Copyright

Data In Motion Consuling GmbH - All rights reserved

---
Data In Motion Consuling GmbH - [info@data-in-motion.biz](mailto:info@data-in-motion.biz)