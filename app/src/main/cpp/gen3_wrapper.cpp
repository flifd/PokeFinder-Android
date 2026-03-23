#include <jni.h>
#include <string>
#include <sstream>

#include <Core/Gen3/Encounters3.hpp>
#include <Core/Gen3/StaticTemplate3.hpp>
#include <Core/Enum/Game.hpp>
#include <Core/Enum/Shiny.hpp>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_pokefinderandroid_Gen3Bridge_getFirstStaticEncounter(JNIEnv *env, jclass)
{
    try {
        int size = 0;
        const StaticTemplate3 *enc = Encounters3::getStaticEncounters(0, &size);
        if (enc == nullptr || size <= 0) {
            return env->NewStringUTF("Gen3 static encounter list is empty");
        }

        const StaticTemplate3 &first = enc[0];
        std::ostringstream os;
        os << "Gen3 static encounter[0]: specie=" << first.getSpecie();
        os << ", level=" << (int)first.getLevel();
        os << ", shiny=" << (int)first.getShiny();
        os << ", version=" << (int)first.getVersion();
        os << ", isBuggedRoamer=" << (first.getBuggedRoamer() ? "true" : "false");

        std::string output = os.str();
        return env->NewStringUTF(output.c_str());
    } catch (const std::exception &e) {
        std::string error = "Exception in getFirstStaticEncounter: " + std::string(e.what());
        return env->NewStringUTF(error.c_str());
    } catch (...) {
        return env->NewStringUTF("Unknown exception in getFirstStaticEncounter");
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_pokefinderandroid_Gen3Bridge_getHelloGen3(JNIEnv *env, jobject thiz)
{
    try {
        return env->NewStringUTF("PokeFinder Gen3 native module loaded");
    } catch (const std::exception &e) {
        std::string error = "Exception in getHelloGen3: " + std::string(e.what());
        return env->NewStringUTF(error.c_str());
    } catch (...) {
        return env->NewStringUTF("Unknown exception in getHelloGen3");
    }
}
