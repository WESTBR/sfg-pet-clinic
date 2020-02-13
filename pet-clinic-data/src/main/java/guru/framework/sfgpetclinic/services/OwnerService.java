package guru.framework.sfgpetclinic.services;

import guru.framework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);
}
