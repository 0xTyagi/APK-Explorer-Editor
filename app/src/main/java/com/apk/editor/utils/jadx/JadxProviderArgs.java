package com.apk.editor.utils.jadx;

import java.io.File;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import jadx.api.CommentsLevel;
import jadx.api.DecompilationMode;
import jadx.api.ICodeCache;
import jadx.api.ICodeWriter;
import jadx.api.JadxArgs;
import jadx.api.args.DeobfuscationMapFileMode;
import jadx.api.args.ResourceNameSource;
import jadx.api.data.ICodeData;

public class JadxProviderArgs {

    public boolean no_res = false;
    public boolean no_src = false;

    public Predicate<String> class_filter;
    public boolean class_filter_include_dependencies = false;

    public boolean export_gradle = false;

    public int threads_count = JadxArgs.DEFAULT_THREADS_COUNT;

    public DecompilationMode decompilation_mode = DecompilationMode.AUTO;

    public boolean show_bad_code = false;

    public boolean use_imports = true;
    public boolean debug_info = true;
    public boolean add_debug_lines = false;
    public boolean inline_anonymous = true;
    public boolean inline_methods = true;
    public boolean extract_finally = true;
    public boolean replace_consts = true;
    public boolean escape_unicode = false;
    public boolean respect_bytecode_access_modifiers = false;

    public boolean deobf = false;
    public int deobf_min = 3;
    public int deobf_max = 64;
    public File deobf_cfg_file;
    public DeobfuscationMapFileMode deobf_cfg_file_mode = DeobfuscationMapFileMode.READ;
    public boolean deobf_use_sourcename = false;
    public boolean deobf_parse_kotlin_metadata = false;
    public ResourceNameSource deobf_res_name_source = ResourceNameSource.AUTO;


    public JadxArgs.UseKotlinMethodsForVarNames use_kotlin_methods_for_var_names = JadxArgs.UseKotlinMethodsForVarNames.APPLY;
    public Set<JadxArgs.RenameEnum> rename_flags;
    public boolean fs_case_sensitive = false;
    public boolean cfg = false;
    public boolean raw_cfg = false;
    public boolean use_dx = false;

    public CommentsLevel comments_level = CommentsLevel.INFO;

    public Map<String, String> plugin_options;

    public boolean skip_files_save = false;

    public Function<JadxArgs, ICodeWriter> code_writer;
    public ICodeCache code_cache;
    public ICodeData code_data;

    public JadxProviderArgs() {}
}
