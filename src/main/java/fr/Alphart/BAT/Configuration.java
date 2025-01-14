package fr.Alphart.BAT;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.cubespace.Yamler.Config.Path;
import net.cubespace.Yamler.Config.YamlConfig;

@Getter
public class Configuration extends YamlConfig {
	public Configuration() {
		CONFIG_HEADER = new String[] { "Bungee Admin Tools - Configuration file" };
		CONFIG_FILE = new File(BAT.getInstance().getDataFolder(), "config.yml");
		try {
			init();
			save();
		} catch (final InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	private String language = "en";
	private String prefix = "&6[&4BAT&6]&e ";

	@Comment("Force players to give reason when /ban /unban /kick /mute /unmute etc.")
	private boolean mustGiveReason = false;
	@Comment("Enable /bat confirm, to confirm command such as action on unknown player.")
	private boolean confirmCommand = true;
	@Comment("Enable or disable simple aliases to bypass the /bat prefix for core commands")
	private Map<String, Boolean> simpleAliasesCommands = Maps.newHashMap();
	@Comment("Make the date more readable." + "If the date correspond to today, tmw or yda, it will replace the date by the corresponding word")
	private boolean litteralDate = true;
	@Comment("Enable BETA (experimental) Redis support, requires RedisBungee")
	private boolean redisSupport = false;
	@Comment("The debug mode enables verbose logging. All the logged message will be in the debug.log file in BAT folder")
	private boolean debugMode = false;

	@Comment("Set to true to use MySQL. Otherwise SQL Lite will be used")
	@Setter
	@Path(value = "mysql.enabled")
	private boolean mysql_enabled = true;
	@Path(value = "mysql.user")
	private String mysql_user = "user";
	@Path(value = "mysql.password")
	private String mysql_password = "password";
	@Path(value = "mysql.database")
	private String mysql_database = "database";
	@Path(value = "mysql.host")
	private String mysql_host = "localhost";
	@Comment("If you don't know it, just leave it like this (3306 = default mysql port)")
	@Path(value = "mysql.port")
	private String mysql_port = "3306";

	public Locale getLocale() {
		if (language.length() != 2) {
			BAT.getInstance().getLogger().severe("Incorrect language set ... The language was set to english.");
			return new Locale("en");
		}
		return new Locale(language);
	}

	public String getPrefix() {
		return prefix;
	}

	public boolean isRedisSupport() {
		return redisSupport;
	}

	public boolean isMysql_enabled() {
		return mysql_enabled;
	}

	public String getMysql_user() {
		return mysql_user;
	}

	public String getMysql_password() {
		return mysql_password;
	}

	public String getMysql_database() {
		return mysql_database;
	}

	public String getMysql_port() {
		return mysql_port;
	}

	public String getMysql_host() {
		return mysql_host;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public Map<String, Boolean> getSimpleAliasesCommands() {
		return simpleAliasesCommands;
	}

	public boolean isConfirmCommand() {
		return confirmCommand;
	}

	public boolean isMustGiveReason() {
		return mustGiveReason;
	}

	public boolean isLitteralDate() {
		return litteralDate;
	}
}
