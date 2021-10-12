package org.springframework.samples.petclinic.owner;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerTest {

    private Owner owner;

    @BeforeEach
    public void setup() {
        owner = new Owner();
    }

    @Test
    public void testSetGetTelephone() {
        owner.setTelephone("09392324236");
        assertEquals(owner.getTelephone(), "09392324236");
    }

    @Test
    public void testSetGetCity() {
        owner.setCity("Tehran");
        assertEquals(owner.getCity(), "Tehran");
    }

    @Test
    public void testSetGetAddress() {
        owner.setAddress("Tehran, Pelak 255");
        assertEquals(owner.getAddress(), "Tehran, Pelak 255");
    }

    
}
