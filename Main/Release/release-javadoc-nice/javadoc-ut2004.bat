cd ..\..\..
rmdir /S /Q Main\PogamutRelease\target\javadoc-ut2004
mkdir Main\PogamutRelease\target\javadoc-ut2004
javadoc.exe -doclet org.jboss.apiviz.APIviz -docletpath Main/PogamutRelease/Utils/apiviz-1.3.1.GA.jar -classpath "Main/PogamutRelease/utils/junit-4.8.2.jar;Main/PogamutRelease/utils/xstream-1.3.1.jar;%JAVA_HOME%/jre/lib/rt.jar;Main/PogamutRelease/utils/guice-2.0.jar" -d Main/PogamutRelease/target/javadoc -subpackages cz -sourcepath Utils/AmisUtils/src/main/java;Utils/AFSM/src/main/java;Utils/JavaGeom/src/main/java;Main/PogamutBase/src/main/java;Main/PogamutUnreal/src/main/java;Main/PogamutUT2004/src/main/java  -sourceclasspath Utils/AmisUtils/target/classes -sourceclasspath Utils/AFSM/target/classes -sourceclasspath Utils/JavaGeom/target/classes -sourceclasspath Main/PogamutBase/target/classes -sourceclasspath Main/PogamutUnreal/target/classes -sourceclasspath Main/PogamutUT2004/target/classes
exit
