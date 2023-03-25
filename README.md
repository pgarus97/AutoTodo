# AutoTodo
AutoTodo is a lightweight java application that can be used in combination with e.g. live caption tools in OBS to automatically extract sentences starting with a certain pattern like "TODO" into a separate text file.

# How To Use
This program can be used with any kind of .txt files, but it is highly recommended to use it together with a closed caption solution.
My recommended setup using [OBS](https://obsproject.com/) and the closed caption plugin:
1. Go [here](https://obsproject.com/forum/resources/closed-captioning-via-google-speech-recognition.833/) and download the plugin for you OBS setup. Note that you might need a specific version for that. This plugin automatically generates closed captions for your recordings and streams.

2. Set up the plugin: In OBS, go to "Tools -> Cloud Closed Captions". Select "Captioning Enabled" and then press the "Settings" button. You can set the settings to your preferences, just select the correct microphone and you should be good to go. Important is the "Transcripts" tab. Select "Save Transcripts" and also select if you want to transcribe only your stream, recordings or both. Set the "Format" to Text only (.txt) and finally choose a directory for the "Transcript Folder".

3. After your stream/recording, you can open the *autoTODO* software, select the according transcript file as the input path and also select where the result should get saved to. You can also edit which words the software should looking for, the recommendations of "to do" and "to-do" are what the OBS caption plugin usually understands. After that, just run and you should get a new text file with all the TODOs of a stream! 

## Notes: 
- The OBS caption plugin is not perfect, so try to speak the TODO items slowly and try not to use game-specific language that is hard to understand. If you struggle with getting the correct results, try editing the search words.

- You can choose the same output path/file multiple times for various kinds of transcripts! The output file will append new TODOs to the list of already existent ones and will also not consider previously recognized ones!

- You need to have Java installed on your computer to use this software. If you encounter any Java version that does not work with this software, please contact me!

- Generally if any troubles or bugs occur, please reach out to me! 
