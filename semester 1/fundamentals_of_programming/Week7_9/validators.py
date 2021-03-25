from exceptions import ValidError


class ValidatorMovie(object):

    def __init__(self):
        pass

    def validate_movie(self, movie):
        errors = ""
        if movie.get_movie_id() < 0:
            errors += "invalid id\n"
        if movie.get_title() == "":
            errors += "invalid title\n"
        if movie.get_desc() == "":
            errors += "invalid desc\n"
        if movie.get_genre() == "":
            errors += "invalid genre\n"
        if len(errors) > 0:
            raise ValidError(errors)


class ValidatorClient(object):

    def __init__(self):
        pass

    def validate_client(self, client):
        errors = ""
        if client.get_client_id() < 0:
            errors += "invalid id\n"
        if client.get_name() == "":
            errors += "invalid name\n"
        if len(errors) > 0:
            raise ValidError(errors)


class ValidatorRental(object):

    def __init__(self):
        pass

    def validate_rental(self, rental):
        errors = ""
        if rental.get_rid() < 0:
            errors += "invalid id\n"
        if rental.get_mid() < 0:
            errors += "invalid movie id\n"
        if rental.get_cid() < 0:
            errors += "invalid client id\n"
        if rental.get_rented_date() == "":
            errors += "invalid rented date\n"
        if rental.get_due_date() == "":
            errors += "invalid due date\n"
        if rental.get_returned_date() == "":
            errors += "invalid returned date\n"
        if len(errors) > 0:
            raise ValidError(errors)
