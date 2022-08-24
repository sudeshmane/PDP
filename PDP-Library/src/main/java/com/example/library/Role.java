package com.example.library;

//@Entity
//@Table(name = "roles")
public class Role {
 //   @Id
   // @Column(name = "role_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id =1;
     
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
    public Integer getId() {
        return id;
    }

  //  @Column(name = "name")
    private String name ;
    //="ADMIN2";
     
    public Role(){
    	 
     }
}
