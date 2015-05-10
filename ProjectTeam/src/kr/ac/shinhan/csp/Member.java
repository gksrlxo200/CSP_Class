package kr.ac.shinhan.csp;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Member {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;

	@Persistent
	private String name;

	@Persistent
	private String id;

	@Persistent
	private String email;

	@Persistent
	private String kakaoid;

	@Persistent
	private String gitid;
	@Persistent
	private boolean chk_info;

	public Member(String name, String id, String email, String kakaoid,
			String gitid, boolean chk_info) {
		this.name = name;
		this.id = id;
		this.email = email;
		this.kakaoid = kakaoid;
		this.gitid = gitid;
		this.chk_info = chk_info;
	}

	public boolean isChkinfo() {
		return chk_info;
	}

	public void setChk_info(boolean chk_info) {
		this.chk_info = chk_info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKakaoid() {
		return kakaoid;
	}

	public void setKakaoid(String kakaoid) {
		this.kakaoid = kakaoid;
	}

	public String getGitid() {
		return gitid;
	}

	public void setGitid(String gitid) {
		this.gitid = gitid;
	}

	public Long getKey() {
		return key;
	}

}
