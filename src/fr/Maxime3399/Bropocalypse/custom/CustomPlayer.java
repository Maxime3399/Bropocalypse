package fr.Maxime3399.Bropocalypse.custom;

import org.bukkit.entity.Player;

import fr.Maxime3399.Bropocalypse.utils.MySQLUtils;

public class CustomPlayer {
	
	//System
	private Player player;
	private Role role;
	private Team team;
	private Mode mode;
	private boolean dead;
	private int respawn;
	
	//Score
	private int current_kills_knife;
	private int current_kills_canon;
	private int current_deaths;
	private int data_win;
	private int data_loses;
	private int data_kills_knife;
	private int data_kills_canon;
	private int data_deaths;
	private int data_time_hours;
	private int data_time_minutes;
	private int data_time_seconds;
	
	public CustomPlayer(Player p) {
		
		this.setPlayer(p);
		this.setRole(null);
		this.setTeam(null);
		this.setMode(Mode.DEFAULT);
		this.setDead(false);
		this.setRespawn(0);
		this.setCurrent_kills_knife(0);
		this.setCurrent_kills_canon(0);
		this.setCurrent_deaths(0);
		this.setData_win(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "win"));
		this.setData_loses(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "loses"));
		this.setData_kills_knife(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "kills_knife"));
		this.setData_kills_canon(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "kills_canon"));
		this.setData_deaths(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "deaths"));
		this.setData_time_hours(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "time_hours"));
		this.setData_time_minutes(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "time_minutes"));
		this.setData_time_seconds(MySQLUtils.getInt("bropocalypse_players", "uuid", p.getUniqueId().toString(), "time_seconds"));
		
	}
	
	public void save() {
		
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "win", data_win);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "loses", data_loses);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "kills_knife", data_kills_knife);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "kills_canon", data_kills_canon);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "deaths", data_deaths);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "time_hours", data_time_hours);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "time_minutes", data_time_minutes);
		MySQLUtils.setInt("bropocalypse_players", "uuid", player.getUniqueId().toString(), "time_seconds", data_time_seconds);
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getRespawn() {
		return respawn;
	}

	public void setRespawn(int respawn) {
		this.respawn = respawn;
	}

	public int getCurrent_kills_knife() {
		return current_kills_knife;
	}

	public void setCurrent_kills_knife(int current_kills_knife) {
		this.current_kills_knife = current_kills_knife;
	}

	public int getCurrent_kills_canon() {
		return current_kills_canon;
	}

	public void setCurrent_kills_canon(int current_kills_canon) {
		this.current_kills_canon = current_kills_canon;
	}

	public int getCurrent_deaths() {
		return current_deaths;
	}

	public void setCurrent_deaths(int current_deaths) {
		this.current_deaths = current_deaths;
	}

	public int getData_win() {
		return data_win;
	}

	public void setData_win(int data_win) {
		this.data_win = data_win;
	}

	public int getData_loses() {
		return data_loses;
	}

	public void setData_loses(int data_loses) {
		this.data_loses = data_loses;
	}

	public int getData_kills_knife() {
		return data_kills_knife;
	}

	public void setData_kills_knife(int data_kills_knife) {
		this.data_kills_knife = data_kills_knife;
	}

	public int getData_kills_canon() {
		return data_kills_canon;
	}

	public void setData_kills_canon(int data_kills_canon) {
		this.data_kills_canon = data_kills_canon;
	}

	public int getData_deaths() {
		return data_deaths;
	}

	public void setData_deaths(int data_deaths) {
		this.data_deaths = data_deaths;
	}

	public int getData_time_hours() {
		return data_time_hours;
	}

	public void setData_time_hours(int data_time_hours) {
		this.data_time_hours = data_time_hours;
	}

	public int getData_time_minutes() {
		return data_time_minutes;
	}

	public void setData_time_minutes(int data_time_minutes) {
		this.data_time_minutes = data_time_minutes;
	}

	public int getData_time_seconds() {
		return data_time_seconds;
	}

	public void setData_time_seconds(int data_time_seconds) {
		this.data_time_seconds = data_time_seconds;
	}

}
