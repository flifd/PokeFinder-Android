#include <jni.h>
#include <string>
#include <sstream>

#include <Core/Gen3/Encounters3.hpp>
#include <Core/Gen3/Generators/WildGenerator3.hpp>
#include <Core/Gen3/StaticTemplate3.hpp>
#include <Core/Enum/Game.hpp>
#include <Core/Enum/Shiny.hpp>
#include <Core/Gen3/Profile3.hpp>
#include <Core/Parents/Filters/StateFilter.hpp>
#include <Core/Enum/Encounter.hpp>
#include <Core/Enum/Method.hpp>
#include <Core/Enum/Lead.hpp>
#include <Core/External/nlohmann/json.hpp>

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
Java_com_example_pokefinderandroid_gen3_Gen3Bridge_getHelloGen3(JNIEnv *env, jobject thiz)
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

//extern "C" JNIEXPORT jstring JNICALL
//        Java_com_example_pokefinderandroid_gen3_Gen3Bridge_generateWildEncounters(JNIEnv *env,
//                      jobject thiz, jint jseed, jint jinitialAdvances, jint jmaxAdvances,
//                      jint joffset, jint jmethod, jint jlead, jboolean jfeebasTile, jint jlocation,
//                      jint jencounter, jobject jprofile)
//{
//    try {
//        // Extract jprofile fields
//        jclass profileClass = env->GetObjectClass(jprofile);
//
//        // Name
//        jfieldID nameField = env->GetFieldID(profileClass, "name", "Ljava/lang/String;");
//        jstring nameJ = (jstring)env->GetObjectField(jprofile, nameField);
//        const char* nameChars = env->GetStringUTFChars(nameJ, nullptr);
//        std::string name(nameChars);
//        env->ReleaseStringUTFChars(nameJ, nameChars);  // Important: release the string
//
//        // Version
//        jfieldID versionField = env->GetFieldID(profileClass, "version", "Lcom/example/pokefinderandroid/gen3/Game;");
//        jobject versionObj = env->GetObjectField(jprofile, versionField);
//        jclass gameClass = env->GetObjectClass(versionObj);
//        jmethodID ordinalMethod = env->GetMethodID(gameClass, "ordinal", "()I");
//        jint versionInt = env->CallIntMethod(versionObj, ordinalMethod);
//        Game gameVersion = static_cast<Game>(versionInt);
//
//        // TID
//        jfieldID tidField = env->GetFieldID(profileClass, "tid", "I");
//        jint s32tid = env->GetIntField(jprofile, tidField);
//        u16 tid = static_cast<u16>(s32tid);
//
//        // SID
//        jfieldID sidField = env->GetFieldID(profileClass, "sid", "I");
//        jint s32sid = env->GetIntField(jprofile, sidField);
//        u16 sid = static_cast<u16>(s32sid);
//
//        // Dead battery
//        jfieldID deadBatteryField = env->GetFieldID(profileClass, "deadBattery", "Z");
//        jboolean jDeadBattery = env->GetBooleanField(jprofile, deadBatteryField);
//        bool deadBattery = static_cast<bool>(jDeadBattery);
//
//        Profile3 profile = Profile3(name, gameVersion, tid, sid, deadBattery); // Temporary values for tid, sid, deadBattery
//
//        // Extract remaining parameters
//        u8 location = static_cast<u8>(jlocation);
//
//
//        // Find the specific area matching your location ID
//        // ... (loop through areas as you started in your snippet)
//
//        EncounterArea3 theArea(location, rate, Encounter::Grass, std::array<Slot, 12>{}); // dummy
//        bool found = false;
//        for (const auto &area : areas) {
//            if (area.getLocation() == location) {
//                theArea = area;
//                found = true;
//                break;
//            }
//        }
//        if (!found) {
//            return env->NewStringUTF("Location not found");
//        }
//
//        // Create filter with defaults (no filtering)
//        std::array<u8, 6> ivMin = {0, 0, 0, 0, 0, 0};
//        std::array<u8, 6> ivMax = {31, 31, 31, 31, 31, 31};
//        std::array<bool, 25> natures;
//        natures.fill(true);
//        std::array<bool, 16> powers;
//        powers.fill(true);
//        std::array<bool, 12> encounterSlots;
//        encounterSlots.fill(true);
//        WildStateFilter filter(255, 255, 255, 0, 255, 0, 255, false, ivMin, ivMax, natures, powers, encounterSlots);
//
//        // Create generator
//        WildGenerator3 gen(static_cast<u32>(initialAdvances), static_cast<u32>(maxAdvances), static_cast<u32>(offset), static_cast<Method>(method), static_cast<Lead>(lead), static_cast<bool>(feebasTile), theArea, prof, filter);
//
//        // Generate
//        auto states = gen.generate(static_cast<u32>(seed));
//
//        // Format output
//        std::ostringstream os;
//        for (const auto &state : states) {
//            os << "Advances: " << state.getAdvances()
//               << ", PID: " << state.getPID()
//               << ", IVs: " << (int)state.getIV(0) << "/" << (int)state.getIV(1) << "/" << (int)state.getIV(2) << "/" << (int)state.getIV(3) << "/" << (int)state.getIV(4) << "/" << (int)state.getIV(5)
//               << ", Ability: " << (int)state.getAbility()
//               << ", Gender: " << (int)state.getGender()
//               << ", Level: " << (int)state.getLevel()
//               << ", Nature: " << (int)state.getNature()
//               << ", Shiny: " << (int)state.getShiny()
//               << ", Encounter Slot: " << (int)state.getEncounterSlot()
//               << ", Specie: " << state.getSpecie()
//               << ", Form: " << (int)state.getForm()
//               << "\n";
//        }
//
//        std::string output = os.str();
//        return env->NewStringUTF(output.c_str());
//    } catch (const std::exception &e) {
//        std::string error = "Exception in generateWildEncounters: " + std::string(e.what());
//        return env->NewStringUTF(error.c_str());
//    } catch (...) {
//        return env->NewStringUTF("Unknown exception in generateWildEncounters");
//    }
//}
//
//// Helper to create a PersonalInfo object from C++ data (Example)
//jobject createJavaPersonalInfo(JNIEnv *env, const PersonalInfo& cppInfo) {
//    jclass cls = env->FindClass("com/example/pokefinderandroid/general/PersonalInfo");
//    jmethodID constr = env->GetMethodID(cls, "<init>",
//                                        "([Ljava/lang/Integer;[Ljava/lang/Integer;[Ljava/lang/Integer;I[Ljava/lang/Integer;IIIZ)V");
//    // ... Note: Building arrays for stats/types in JNI is verbose.
//    // If you don't need 'info' immediately in Kotlin, consider passing null or a dummy.
//    return nullptr;
//}

// Define how to serialize the Core types to JSON
// Adjust the field names inside these macros to match the C++ getter names/field names
NLOHMANN_DEFINE_TYPE_NON_INTRUSIVE(Slot, specie, form, minLevel, maxLevel)
NLOHMANN_DEFINE_TYPE_NON_INTRUSIVE(EncounterArea3, location, rate, encounter, pokemon)
using json = nlohmann::json;

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_pokefinderandroid_gen3_Gen3Bridge_getValidAreas(JNIEnv *env, jobject thiz, jboolean jfeebasTile, jint jencounter, jobject jprofile)
{
    try {
        // Extract jprofile fields
        jclass profileClass = env->GetObjectClass(jprofile);
        // Version
        jfieldID versionField = env->GetFieldID(profileClass, "version", "Lcom/example/pokefinderandroid/gen3/Game;");
        jobject versionObj = env->GetObjectField(jprofile, versionField);
        jclass gameClass = env->GetObjectClass(versionObj);
        jmethodID ordinalMethod = env->GetMethodID(gameClass, "ordinal", "()I");
        jint versionInt = env->CallIntMethod(versionObj, ordinalMethod);
        Game gameVersion = static_cast<Game>(versionInt);

        EncounterSettings3 settings{static_cast<bool>(jfeebasTile)};
        auto areas = Encounters3::getEncounters(static_cast<Encounter>(jencounter), settings, gameVersion);

        // Convert the vector of EncounterArea3 to a JSON object
        json j = areas;

        // Serialize to string (0 = no pretty printing/indentation for speed)
        std::string jsonResult = j.dump();
        return env->NewStringUTF(jsonResult.c_str());
    } catch (const std::exception &e) {
        std::string error = "Exception in getHelloGen3: " + std::string(e.what());
        return env->NewStringUTF(error.c_str());
    } catch (...) {
        return env->NewStringUTF("Unknown exception in getHelloGen3");
    }
}