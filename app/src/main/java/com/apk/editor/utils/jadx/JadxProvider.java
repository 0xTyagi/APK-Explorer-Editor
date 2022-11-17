package com.apk.editor.utils.jadx;

import android.util.Log;

import androidx.annotation.Nullable;

import com.apk.editor.BuildConfig;

import java.io.File;

import jadx.api.JadxArgs;
import jadx.api.JadxDecompiler;

public class JadxProvider {


    private final boolean mDebugInfo;
    private final File mInputFile, mOutDir;
    private final JadxProviderArgs mJadxProviderArgs;

    JadxProvider(boolean debugInfo, File inputFile, File outDir, @Nullable JadxProviderArgs jadxProviderArgs) {
        this.mDebugInfo = debugInfo;
        this.mInputFile = inputFile;
        this.mOutDir = outDir;
        this.mJadxProviderArgs = jadxProviderArgs;
    }

    public void execute() {
        JadxArgs jadxArgs = new JadxArgs();
        jadxArgs.setInputFile(mInputFile);
        //jadxArgs.setOutDir(mOutDir);
        jadxArgs.setRootDir(mOutDir);
        //jadxArgs.setFsCaseSensitive(false);
        if (mJadxProviderArgs != null) {
            jadxArgs.setSkipResources(mJadxProviderArgs.no_res);
            jadxArgs.setSkipSources(mJadxProviderArgs.no_src);

            if (mJadxProviderArgs.class_filter != null) {
                jadxArgs.setClassFilter(mJadxProviderArgs.class_filter);
            }
            jadxArgs.setIncludeDependencies(mJadxProviderArgs.class_filter_include_dependencies);

            jadxArgs.setExportAsGradleProject(mJadxProviderArgs.export_gradle);

            jadxArgs.setThreadsCount(mJadxProviderArgs.threads_count);

            jadxArgs.setDecompilationMode(mJadxProviderArgs.decompilation_mode);

            jadxArgs.setShowInconsistentCode(mJadxProviderArgs.show_bad_code);

            jadxArgs.setUseImports(mJadxProviderArgs.use_imports);
            jadxArgs.setDebugInfo(mJadxProviderArgs.debug_info);
            jadxArgs.setInsertDebugLines(mJadxProviderArgs.add_debug_lines);
            jadxArgs.setInlineAnonymousClasses(mJadxProviderArgs.inline_anonymous);
            jadxArgs.setInlineMethods(mJadxProviderArgs.inline_methods);
            jadxArgs.setExtractFinally(mJadxProviderArgs.extract_finally);
            jadxArgs.setReplaceConsts(mJadxProviderArgs.replace_consts);
            jadxArgs.setEscapeUnicode(mJadxProviderArgs.escape_unicode);
            jadxArgs.setRespectBytecodeAccModifiers(mJadxProviderArgs.respect_bytecode_access_modifiers);

            jadxArgs.setDeobfuscationOn(mJadxProviderArgs.deobf);
            jadxArgs.setDeobfuscationMinLength(mJadxProviderArgs.deobf_min);
            jadxArgs.setDeobfuscationMaxLength(mJadxProviderArgs.deobf_max);
            if (mJadxProviderArgs.deobf_cfg_file != null) {
                jadxArgs.setDeobfuscationMapFile(mJadxProviderArgs.deobf_cfg_file);
            }
            jadxArgs.setDeobfuscationMapFileMode(mJadxProviderArgs.deobf_cfg_file_mode);
            jadxArgs.setUseSourceNameAsClassAlias(mJadxProviderArgs.deobf_use_sourcename);
            jadxArgs.setParseKotlinMetadata(mJadxProviderArgs.deobf_parse_kotlin_metadata);
            jadxArgs.setResourceNameSource(mJadxProviderArgs.deobf_res_name_source);

            jadxArgs.setUseKotlinMethodsForVarNames(mJadxProviderArgs.use_kotlin_methods_for_var_names);
            if (mJadxProviderArgs.rename_flags != null) {
                jadxArgs.setRenameFlags(mJadxProviderArgs.rename_flags);
            }
            jadxArgs.setFsCaseSensitive(mJadxProviderArgs.fs_case_sensitive);
            jadxArgs.setCfgOutput(mJadxProviderArgs.cfg);
            jadxArgs.setRawCFGOutput(mJadxProviderArgs.raw_cfg);
            jadxArgs.setUseDxInput(mJadxProviderArgs.use_dx);

            jadxArgs.setCommentsLevel(mJadxProviderArgs.comments_level);

            if (mJadxProviderArgs.plugin_options != null) {
                jadxArgs.setPluginOptions(mJadxProviderArgs.plugin_options);
            }

            jadxArgs.setSkipFilesSave(mJadxProviderArgs.skip_files_save);

            if (mJadxProviderArgs.code_cache != null) {
                jadxArgs.setCodeCache(mJadxProviderArgs.code_cache);
            }
            if (mJadxProviderArgs.code_data != null) {
                jadxArgs.setCodeData(mJadxProviderArgs.code_data);
            }
            if (mJadxProviderArgs.code_writer != null) {
                jadxArgs.setCodeWriterProvider(mJadxProviderArgs.code_writer);
            }
        }
        if (mDebugInfo) {
            Log.d("jadx_provider", jadxArgs.toString());
        }
        try (JadxDecompiler jadx = new JadxDecompiler(jadxArgs)) {
            jadx.load();
            jadx.save();
        } catch (Exception e) {
            Log.e("jadx_provider", "Exception: ", e);
        }
        //jadxArgs.close();
    }



}
