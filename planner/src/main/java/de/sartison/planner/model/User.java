package de.sartison.planner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kk_user")
public class User {
	private Long id;
	private String name;
	private String pwd;
	private String bgColor;
	private String txColor;

	public User() {
	}

	public User(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}

	@Column(name = "user_id")
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_password")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "user_color")
	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	@Column(name = "user_text_color")
	public String getTxColor() {
		return txColor;
	}

	public void setTxColor(String txColor) {
		this.txColor = txColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", bgColor=" + bgColor + ", txColor=" + txColor + "]";
	}
}