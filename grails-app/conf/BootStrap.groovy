/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
class BootStrap {
  def jabberService

  def init = {servletContext ->
    Locale.setDefault(new Locale("nb"))

    if (!Person.get(1)) {
      def admin = new Person(name: "Default Admin - Change these details to your own!", userId: "admin", password: "password",
              phone: "xxxxxxxx", email: "root@localhost.localdomain", admin: true, approved: true, confirmed: true, language: "en")


      if (!admin.validate()) {
        admin.errors.each {
          println it
        }
      }

      if (!admin.save()) {
        println("Unable to save admin user")
      }
    }

    jabberService.connect()
  }
  def destroy = {
    jabberService.disconnect()
  }
} 