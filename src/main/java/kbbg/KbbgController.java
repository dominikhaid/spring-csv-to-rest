package kbbg;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/betriebsstelle")
@RestController
class KbbgController {

  @Autowired
  private final KbbgRepository repository;

  KbbgController(KbbgRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{id}")
  EntityModel<Kbbg> one(@PathVariable Long id) {
    Kbbg kbbg = repository
      .findById(id)
      .orElseThrow(() -> new KbbgNotFoundException(id));

    return EntityModel.of(
      kbbg,
      linkTo(methodOn(KbbgController.class).one(id)).withSelfRel(),
      linkTo(methodOn(KbbgController.class).all()).withRel("kbbgs")
    );
  }

  @GetMapping("")
  CollectionModel<EntityModel<Kbbg>> all() {
    List<EntityModel<Kbbg>> kbbgs = repository
      .findAll()
      .stream()
      .map(
        kbbg ->
          EntityModel.of(
            kbbg,
            linkTo(methodOn(KbbgController.class).one(kbbg.getId()))
              .withSelfRel(),
            linkTo(methodOn(KbbgController.class).all()).withRel("kbbgs")
          )
      )
      .collect(Collectors.toList());

    return CollectionModel.of(
      kbbgs,
      linkTo(methodOn(KbbgController.class).all()).withSelfRel()
    );
  }

  @GetMapping("/search/{rlcode}")
  CollectionModel<EntityModel<Kbbg>> search(@PathVariable String rlcode) {
    List<EntityModel<Kbbg>> kbbgs = repository
      .findByRl100CodeContaining(rlcode, Sort.by("rl100Code"))
      .stream()
      .map(
        kbbg ->
          EntityModel.of(
            kbbg,
            linkTo(methodOn(KbbgController.class).all()).withRel("kbbgs")
          )
      )
      .collect(Collectors.toList());

    return CollectionModel.of(
      kbbgs,
      linkTo(methodOn(KbbgController.class).search(rlcode)).withSelfRel()
    );
  }

  @RequestMapping("/search")
  public CollectionModel<EntityModel<Kbbg>> search2(
    @RequestParam(value = "rlcode", defaultValue = "A") String rlcode
  ) {
    List<EntityModel<Kbbg>> kbbgs = repository
      .findByRl100CodeContaining(rlcode, Sort.by("rl100Code"))
      .stream()
      .map(
        kbbg ->
          EntityModel.of(
            kbbg,
            linkTo(methodOn(KbbgController.class).all()).withRel("kbbgs")
          )
      )
      .collect(Collectors.toList());

    return CollectionModel.of(
      kbbgs,
      linkTo(methodOn(KbbgController.class).search2(rlcode)).withSelfRel()
    );
  }

  @PostMapping("")
  Kbbg newKbbg(@RequestBody Kbbg newKbbg) {
    return repository.save(newKbbg);
  }

  @PutMapping("/{id}")
  Kbbg replaceKbbg(@RequestBody Kbbg newKbbg, @PathVariable Long id) {
    return repository
      .findById(id)
      .map(
        kbbg -> {
          kbbg.setPlc(newKbbg.getPlc());
          return repository.save(kbbg);
        }
      )
      .orElseGet(
        () -> {
          newKbbg.setId(id);
          return repository.save(newKbbg);
        }
      );
  }

  @DeleteMapping("/{id}")
  void deleteKbbg(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
