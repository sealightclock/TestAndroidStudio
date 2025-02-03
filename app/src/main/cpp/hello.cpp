#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_jonathan_testandroidstudio_MainActivityKt_stringFromJNI(JNIEnv *env,
                                                                         jclass clazz) {
    std::string hello = "Hello from JNI!";
    return env->NewStringUTF(hello.c_str());
}
