package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerEntityMapper {

//    @Mapping(target = "restaurant.restaurantOwner", ignore = true)
//    @Mapping(target = "restaurant.address.restaurant", ignore = true)
//    RestaurantOwner mapFromEntity(RestaurantOwnerEntity restaurantOwnerEntity);
//
//    RestaurantOwnerEntity mapToEntity(RestaurantOwner restaurantOwner);



}
