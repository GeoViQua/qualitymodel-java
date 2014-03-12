# qualitymodel-java

GeoViQua producer and user quality model XML bindings

## Usage

See the ``demo`` subproject for some examples on how to create GeoViQua metadata documents in Java.

In integrate the library into your Java project simply add the following Maven dependency and repository:

```
	<dependencies>
		<dependency>
			<groupId>org.geoviqua.qim</groupId>
			<artifactId>schemas</artifactId>
			<version>4.0</version>
		</dependency>
	</dependencies>
	
	[...]
	
	<repositories>
		<repository>
			<id>n52-snapshots</id>
			<name>52n Snapshots</name>
			<url>http://52north.org/maven/repo/snapshots/</url>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>
```

Or download the latest version of the library manually from the 52°North Maven snapshots repository: http://52north.org/maven/repo/snapshots/org/geoviqua/qim/schemas

## Building the schemas

Go the the ``schemas`` module and run ``mvn clean install``.

## License

These bindings are published under The Apache Software License, Version 2.0.

## Contact

Daniel Nüst (d.nuest@52north.org)