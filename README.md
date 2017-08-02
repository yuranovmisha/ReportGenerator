# ReportGenerator
Сборка проекта:
В папке проекта запускаем:
javac -d .  -cp . Generator.java  helpers/*.java managers/*.java models/*.java
После компиляции запускаем:
java generator.Generator <файл настроек> <файл данных>  <файл отчета>
Файл данных должен быть в кодировке UTF-8. Исходный файл был в другой кодировке. Пришлось его перекодировать.
