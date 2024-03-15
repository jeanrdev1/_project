abstract class AbstractEntity {
  late int? id;

  toJson();
  fromJson(String json);
}
